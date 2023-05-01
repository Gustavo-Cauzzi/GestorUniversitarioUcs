package view.relacionadores.base;

import repository.RepositoryLink;
import utils.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleRelacionadorViewBase<T> extends RepositoryLink<T> {
    private Scanner scanner;

    public SimpleRelacionadorViewBase(List<T> db) {
        super(db);
    }

    public void exec() {
        this.exec(null, null);
    }

    public void exec(Integer codigo1) {
        this.exec(codigo1, null);
    }

    public void exec(Integer codigo1, Integer codigo2) {
        T element = this.relacionar(codigo1, codigo2);
        if (element != null) this.db.add(element);
        this.scanner = null;
    }

    protected T relacionar(Integer codigo1, Integer codigo2) {
        return null;
    }

    protected <E> Optional<E> askForEntity(final Class<E> clazz, final List<E> dbLst, final Integer cod) {
        Scanner sc = getScanner();
        Integer tmpCod = cod;
        Optional<E> foundEntity = Optional.empty();
        String table = clazz.getSimpleName().toLowerCase();

        if (dbLst.isEmpty()) {
            System.out.println("Não há registros! cancelando a ação");
            return foundEntity;
        }

        do {
            if (tmpCod == null) {
                String action;
                do {
                    System.out.printf("(Digite -1 para listar todos(as) os(as) %s)%n", table + "s");
                    System.out.printf("Digite o código de qual %s você quer relacionar%n", table);
                    action = sc.next();
                    if ("-1".equals(action)) {
                        this.showFirstCodeAndDescription(dbLst);
                    }
                } while ("-1".equals(action));
                tmpCod = Integer.parseInt(action);
            }
            foundEntity = this.findByCode(dbLst, tmpCod);
            if (foundEntity.isEmpty()) {
                System.out.println("Nao foi possível encontrar o " + table);
            }
            tmpCod = null;
        } while (foundEntity.isEmpty());
        return foundEntity;
    }

    protected void showFirstCodeAndDescription(List<?> list) {
        Class<?>[] noparams = {};
        list.forEach(entity -> System.out.println(Stream.of(entity.getClass().getDeclaredFields()).limit(2).map(field -> {
            try {
                Method getter = entity.getClass().getDeclaredMethod("get" + Utils.captilize(field.getName()), noparams);
                return getter.invoke(entity).toString();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                System.out.printf("Getter de %s em %s não foi encontrado%n", entity.getClass().getSimpleName(), field.getName());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                System.out.println("Não foi possível executar o getter");
            }
            return null;
        }).collect(Collectors.joining(" - "))));
    }

    protected <E> Optional<E> findByCode(List<E> list, Integer code) {
        Class<?>[] noparams = {};
        return list.stream().filter(entity -> {
            Field field = entity.getClass().getDeclaredFields()[0];
            try {
                Method getter = entity.getClass().getDeclaredMethod("get" + Utils.captilize(field.getName()), noparams);
                return code.equals(getter.invoke(entity));
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                System.out.printf("Getter de %s em %s não foi encontrado%n", entity.getClass().getSimpleName(), field.getName());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                System.out.println("Não foi possível executar o getter");
            }
            return false;
        }).findFirst();
    }

    public Scanner getScanner() {
        if (this.scanner == null) {
            this.scanner = new Scanner(System.in);
        }
        return this.scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
