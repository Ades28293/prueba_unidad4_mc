package com.uce.edu.demo.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoStockTO;

@Repository
@Transactional
public class ProductoRepositoryImpl implements IProductoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void insertar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.persist(producto);
	}

	@Override
	@Transactional(value = TxType.MANDATORY)
	public void actualizar(Producto producto) {
		// TODO Auto-generated method stub
		this.entityManager.merge(producto);

	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public Producto buscarCodigoBarras(String codigo) {
		// TODO Auto-generated method stub
		TypedQuery<Producto> myQuery = this.entityManager
				.createQuery("SELECT p FROM Producto p WHERE p.codigoBarras=:codigo ", Producto.class);
		myQuery.setParameter("codigo", codigo);
		return myQuery.getSingleResult();

	}

	@Override
	@Transactional(value = TxType.NOT_SUPPORTED)
	public ProductoStockTO buscarStock(String codigo) {
		// TODO Auto-generated method stub

		/*
		 * TypedQuery<ProductoStockTO> myQuery = this.entityManager
		 * .createQuery("SELECT NEW com.uce.edu.demo.repository.modelo.ProductoStockTO(p.codigoBarras,p.categoria,p.nombre,p.stock) FROM Producto p WHERE p.codigoBarras=:codigo "
		 * , ProductoStockTO.class); myQuery.setParameter("codigo", codigo); return
		 * myQuery.getSingleResult();
		 */
		Query myQuery = this.entityManager.createNativeQuery(
				"SELECT prod_codigoBarras,prod_categoria,prod_nombre,prod_stock FROM producto WHERE prod_codigoBarras=:codigo",
				ProductoStockTO.class);

		myQuery.setParameter("codigo", codigo);

		return (ProductoStockTO) myQuery.getSingleResult();
	}

	@Override
	public List<Producto> buscarTodos() {
		TypedQuery<Producto> miQuery = this.entityManager.createQuery("SELECT p FROM Producto p", Producto.class);
		return miQuery.getResultList();
	}

}
