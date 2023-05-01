package models.geral;

public class Relacionador<T1,T2> {
    protected T1 rel1;
    protected T2 rel2;

    public Relacionador() {
        // Empty
    }
    public Relacionador(T1 rel1, T2 rel2) {
        this.rel1 = rel1;
        this.rel2 = rel2;
    }

    public <R> R get(Class<R> clazz) throws RuntimeException {
        if (rel1.getClass().equals(clazz)) {
            return (R) rel1;
        } else if (rel2.getClass().equals(clazz)) {
            return (R) rel2;
        }

        throw new RuntimeException(String.format("Não foi possível localizar a class %s no relacionamento dentro de %s e %s", clazz, rel1.getClass(), rel2.getClass()));
    }

    public <R> void set(R value) throws RuntimeException {
        if (rel1.getClass().equals(value.getClass())) {
            rel1 = (T1) value;
        } else if (rel2.getClass().equals(value.getClass())) {
            rel2 = (T2) value;
        }

        throw new RuntimeException(String.format("Não foi possível localizar a class %s no relacionamento dentro de %s e %s", value.getClass(), rel1.getClass(), rel2.getClass()));
    }

    public Class<T1> getClassRel1 () {
        return (Class<T1>) this.getClass().getSuperclass().getDeclaredFields()[0].getGenericType();
    }

    public Class<T2> getClassRel2 () {
        return (Class<T2>) this.getClass().getSuperclass().getDeclaredFields()[1].getGenericType();
    }
}
