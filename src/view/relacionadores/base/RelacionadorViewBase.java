package view.relacionadores.base;

import models.geral.Relacionador;
import repository.AppRepository;
import repository.RepositoryLink;
import utils.Utils;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class RelacionadorViewBase<T extends Relacionador> extends RepositoryLink<T> {
    private Scanner sc;

    public RelacionadorViewBase(List<T> db) {
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
    }

    protected T relacionar(Integer codigo1, Integer codigo2) {
        return null;
    }

    /**
     * Recebe dois parâmetros de códigos que representam as duas entidades do relacionador que devem ser relacionadas.
     * Caso um for nulo, o método irá perguntar para o usuário qual é o registro que ele planeja relacionar.
     *
     * @param codigo1 Código da primeira classe da relação do Relacionador
     * @param codigo2 Código da primeira classe da relação do Relacionador
     * @return
     */
    protected Optional<Object[]> autoRelacionar(final Integer codigo1, final Integer codigo2, Class<T> relacionadorClass) {
        try {
            T relacionador = relacionadorClass.getDeclaredConstructor().newInstance();

            Optional entity1 = this.askForEntity(relacionador.getClassRel1(), codigo1);
            Optional entity2 = this.askForEntity(relacionador.getClassRel2(), codigo2);

            if (entity1.isEmpty() || entity2.isEmpty()) {
                return Optional.empty();
            }

            return Optional.of(new Object[] {entity1.get(), entity2.get()});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected  <E> Optional<E> askForEntity(final Class<E> clazz, final Integer cod) {
        Scanner sc = getSc();
        List<E> entityDb = this.listAllFromDb(clazz);
        Integer tmpCod = cod;
        Optional<E> foundEntity = Optional.empty();

        if (entityDb.isEmpty()) {
            System.out.println("Não há registros! cancelando a ação");
            return foundEntity;
        }

        do {
            if (tmpCod == null) {
                String action;
                do {
                    String entity = clazz.getSimpleName();
                    System.out.printf("(De um enter para listar todos(as) os(as) %s)%n", entity);
                    System.out.printf("Digite o código de qual %s você quer relacionar%m", entity);
                    action = sc.next();
                    if (action.isEmpty()) {
                        this.showFirstCodeAndDescription(entityDb);
                    }
                } while (!action.isEmpty());
                tmpCod = Integer.parseInt(action);
            }
            foundEntity = this.findByCode(entityDb, tmpCod);
        } while (foundEntity.isEmpty());
        return foundEntity;
    }

    protected  <E> Optional<E> askForEntity(final Class<E> clazz, final List<E> dbLst, final Integer cod) {
        Scanner sc = getSc();
        Integer tmpCod = cod;
        Optional<E> foundEntity = Optional.empty();

        if (dbLst.isEmpty()) {
            System.out.println("Não há registros! cancelando a ação");
            return foundEntity;
        }

        do {
            if (tmpCod == null) {
                String action;
                do {
                    String entity = clazz.getSimpleName();
                    System.out.printf("(De um enter para listar todos(as) os(as) %s)%n", entity);
                    System.out.printf("Digite o código de qual %s você quer relacionar%m", entity);
                    action = sc.next();
                    if (action.isEmpty()) {
                        this.showFirstCodeAndDescription(dbLst);
                    }
                } while (!action.isEmpty());
                tmpCod = Integer.parseInt(action);
            }
            foundEntity = this.findByCode(dbLst, tmpCod);
        } while (foundEntity.isEmpty());
        return foundEntity;
    }

    protected  <E> List<E> listAllFromDb(Class<E> clazz) {
        return Arrays.stream(AppRepository.getInstance().getClass().getDeclaredMethods()).filter(method -> {
            Class<?> r = method.getReturnType();
            if (method.getName().startsWith("get") && !List.class.equals(r)) {
                return false;
            }

            Type returnType = method.getGenericReturnType();
            if (returnType instanceof ParameterizedType parameterisedReturnType) {
                return List.class.isAssignableFrom(method.getReturnType()) &&
                        parameterisedReturnType.getActualTypeArguments()[0].getTypeName().equals(clazz.getTypeName());
            } else {
                return false;
            }
        }).findFirst().map((method) -> {
            try {
                return (List<E>) method.invoke(AppRepository.getInstance());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                System.out.println("Não foi possível encontrar a listagem no banco de dados");
            }
            return Collections.<E>emptyList();
        }).orElse(Collections.emptyList());
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

    protected  <E> Optional<E> findByCode(List<E> list, Integer code) {
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

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }
}
