package com.chibcha.plus.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.chibcha.plus.util.Passgenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.chibcha.plus.entity.Authority;
import com.chibcha.plus.entity.Cliente;
import com.chibcha.plus.entity.Distribuidor;
import com.chibcha.plus.entity.Usuario;
import com.chibcha.plus.repository.AuthorityRepository;
import com.chibcha.plus.repository.UsuarioRepository;
import com.chibcha.plus.service.api.ClienteServiceAPI;
import com.chibcha.plus.service.api.DistribuidorServiceAPI;

@Controller
public class AppController 
{
	@Autowired
	private DistribuidorServiceAPI distribuidorServiceAPI;
	
	@Autowired 
	private ClienteServiceAPI clienteServiceAPI;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	UsuarioRepository userRepository;
	
	private Passgenerator pass = new Passgenerator();
	
	private String referencia = "";
	
	
	@GetMapping({"/","/chibcha"})
	public String index() 
	{
		return "chibcha";
	}
	
	@GetMapping({"/menu"})
	public String menu() 
	{
		return "menu";
	}
	
	@GetMapping({"/admin"})
	public String admin() 
	{
		return "admin";
	}
	
	@GetMapping({"/user"})
	public String user() 
	{
		return "user";
	}
	
	@GetMapping({"/comision"})
	public String comision() 
	{
		return "comision";
	}
	
	@GetMapping({"/soporte"})
	public String soporte() 
	{
		return "soporte";
	}
	
	@GetMapping({"/login"})
	public String login() 
	{
		return "login";
	}
	
	@GetMapping({"/admin_distribuidores"})
	public String admin_distribuidores(Model model, Distribuidor distribuidor) 
	{		
		model.addAttribute("distribuidor", new Distribuidor());
		model.addAttribute("distribuidorEditar", new Distribuidor());
		model.addAttribute("distribuidoresList", distribuidorServiceAPI.listar());
		
		return "admin_distribuidores";
	}
	
	@GetMapping({"/admin_clientes"})
	public String admin_clientes(Model model, Distribuidor distribuidor) 
	{
		Authority userRole = authorityRepository.findByAuthority("ROLE_USER");
		List<Authority> roles = Arrays.asList(userRole);
		
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("clienteEditar", new Cliente());
		model.addAttribute("clientesList", clienteServiceAPI.listar());
		model.addAttribute("roles", roles);
		
		return "admin_clientes";
	}
	
	
	@PostMapping({"/crear_distribuidor"})
	public String crear_distribuidor(Model model, Distribuidor distribuidor)
	{
		distribuidorServiceAPI.guardar(distribuidor);
		model.addAttribute("distribuidor", new Distribuidor());
		model.addAttribute("distribuidoresList", distribuidorServiceAPI.listar());

		return "admin_distribuidores";
	}
	
	@GetMapping({"/editar_distribuidor/{id}"})
	public String editar_distribuidor(Model model, @PathVariable(name="id") Long id)
	{
		Distribuidor distribuidorEditar = distribuidorServiceAPI.obtener(id);
		model.addAttribute("distribuidor", distribuidorEditar);
		model.addAttribute("distribuidoresList", distribuidorServiceAPI.listar());
		
		return "admin_distribuidores";
	}
	
	@GetMapping({"/eliminar_distribuidor/{id}"})
	public String eliminar_distribuidor(Model model, @PathVariable(name="id") Long id)
	{
		distribuidorServiceAPI.eliminar(id);
		model.addAttribute("distribuidor", new Distribuidor());
		model.addAttribute("distribuidoresList", distribuidorServiceAPI.listar());
		
		return "admin_distribuidores";
	}
	
	@PostMapping({"/crear_cliente"})
	public String crear_cliente(Model model, Cliente cliente)
	{
		Usuario cif = cliente.getUsuario();
		if(cif.getPassword().length()<9)
		{
			String passCif = pass.cifrar(cif.getPassword());
			cif.setPassword(passCif);
			cliente.setUsuario(cif);
		}
		
		if(referencia.isEmpty()==false)
		{
			Optional<Usuario> appUser =  userRepository.findByUsername(referencia);
			if(appUser.isPresent()==true)
			{
				Usuario usuEdit = cliente.getUsuario();
				usuEdit.setId(appUser.get().getId());
				cliente.setUsuario(usuEdit);
			}
			
			referencia = "";
		}
		
	
		clienteServiceAPI.guardar(cliente);
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("clientesList", clienteServiceAPI.listar());
		
		Authority userRole = authorityRepository.findByAuthority("ROLE_USER");
		List<Authority> roles = Arrays.asList(userRole);
		model.addAttribute("roles", roles);

		return "admin_clientes";
	}
	
	@GetMapping({"/eliminar_cliente/{id}"})
	public String eliminar_cliente(Model model, @PathVariable(name="id") Long id)
	{
		clienteServiceAPI.eliminar(id);
		model.addAttribute("cliente", new Cliente());
		model.addAttribute("clientesList", clienteServiceAPI.listar());
		
		Authority userRole = authorityRepository.findByAuthority("ROLE_USER");
		List<Authority> roles = Arrays.asList(userRole);
		model.addAttribute("roles", roles);
		
		return "admin_clientes";
	}
	
	@GetMapping({"/editar_cliente/{id}"})
	public String editar_cliente(Model model, @PathVariable(name="id") Long id)
	{
		Cliente clienteEditar = clienteServiceAPI.obtener(id);
		Cliente clienteViejo = clienteServiceAPI.obtener(id);
		
		referencia = clienteViejo.getUsuario().getUsername();
			
		model.addAttribute("cliente", clienteEditar);
		model.addAttribute("clientesList", clienteServiceAPI.listar());
		
		Authority userRole = authorityRepository.findByAuthority("ROLE_USER");
		List<Authority> roles = Arrays.asList(userRole);
		model.addAttribute("roles", roles);
		
		return "admin_clientes";
	}
	

	@GetMapping({"/admin_empleados"})
	public String admin_empleados() 
	{
		return "admin_empleados";
	}
	
	
	

}
