package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Servico.class)
public abstract class Servico_ {

	public static volatile SingularAttribute<Servico, Date> dataRealizacao;
	public static volatile SingularAttribute<Servico, Integer> codigo;
	public static volatile SingularAttribute<Servico, Float> valor;
	public static volatile SingularAttribute<Servico, String> titulo;
	public static volatile SingularAttribute<Servico, Empresa> empresa;

}

