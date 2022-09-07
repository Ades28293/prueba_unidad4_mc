package com.uce.edu.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uce.edu.demo.repository.modelo.Producto;
import com.uce.edu.demo.service.IProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService iProductoService;

	@GetMapping("/buscar")
	public String buscarTodos(Model modelo) {
		List<Producto> lista = this.iProductoService.buscarTodos();
		modelo.addAttribute("productos", lista);

		return "vistaListaProductos";
	}

	@GetMapping("/buscarUno/{idProducto}")
	public String buscarProducto(@PathVariable("idProducto") String codigoBarras, Model modelo) {
		System.out.println("EL Codigo: " + codigoBarras);

		Producto p = this.iProductoService.buscarCodigoBarras(codigoBarras);
		modelo.addAttribute("productos", p);
		return "vistaProducto";
	}

	@PutMapping("/actualizar/{idProducto}")
	public String actualizarProducto(@PathVariable("idProducto") String codigoBarras, Producto producto) {
		producto.setCodigoBarras(codigoBarras);
		this.iProductoService.actualizar(producto);
		return "redirect:/productos/buscar";

	}

	@PostMapping("/insertar")
	public String insertarCitaMedica(Producto producto) {

		this.iProductoService.insertar(producto);
		return "redirect:/productos/buscar";
	}

	// Metodos de redireccionamiento a paginas
	@GetMapping("/nuevaProducto")
	public String paginaNuevaCitaMedica(Producto producto) {
		return "vistaNuevaProducto";
	}

}
