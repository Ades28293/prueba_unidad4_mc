package com.uce.edu.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.repository.modelo.ProductoStockTO;
import com.uce.edu.demo.repository.modelo.ProductoTO;
import com.uce.edu.demo.repository.modelo.ReporteTO;
import com.uce.edu.demo.service.IProductoService;
import com.uce.edu.demo.service.IVentaService;

@SpringBootApplication
public class PruebaUnidad4McApplication implements CommandLineRunner{
	
	private static final Logger LOGGER = Logger.getLogger(PruebaUnidad4McApplication.class);
	
	@Autowired
	private IProductoService iProductoService;
	
	@Autowired IVentaService iVentaService;

	public static void main(String[] args) {
		SpringApplication.run(PruebaUnidad4McApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Producto prod=new Producto();
		prod.setCategoria("Dulce");
		prod.setCodigoBarras("464asd");
		prod.setNombre("Gelatina");
		prod.setPrecio(new BigDecimal(1.50));
		prod.setStock(25);
		
		Producto prod2=new Producto();
		prod2.setCategoria("Frutas");
		prod2.setCodigoBarras("frut126");
		prod2.setNombre("Mandarina");
		prod2.setPrecio(new BigDecimal(6.50));
		prod2.setStock(44);
		
		this.iProductoService.insertar(prod);
		this.iProductoService.insertar(prod2);
		
		ProductoTO productoTO=new ProductoTO();
		productoTO.setCodigoBarras("frut126");
		productoTO.setStock(3);
		
		List<ProductoTO> carrito=new ArrayList<ProductoTO>();
		carrito.add(productoTO);
		
		this.iVentaService.realizarVenta("03","1654678", carrito);
		
		Producto pr=this.iProductoService.buscarCodigoBarras("464asd");
			
			LOGGER.info("Buscar Codigo Barras: "+pr);
			
			
			List<ReporteTO> report=this.iVentaService.reporte(LocalDateTime.of(2022, 8,26 , 0, 0), "vegetales", 2);
		report.forEach(e -> LOGGER.info("Reporte: "+e));
		
		
	}

}
