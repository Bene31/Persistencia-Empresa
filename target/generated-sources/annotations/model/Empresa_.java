package model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Empresa.class)
public abstract class Empresa_ {

	public static volatile ListAttribute<Empresa, Servico> servicos;
	public static volatile SingularAttribute<Empresa, String> nomeFantasia;
	public static volatile SingularAttribute<Empresa, Integer> quantidadeServicos;
	public static volatile SingularAttribute<Empresa, Float> valorTotalServicos;
	public static volatile SingularAttribute<Empresa, String> cnpj;

}

