package com.chibcha.plus.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.chibcha.plus.util.Passgenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.chibcha.plus.entity.Authority;
import com.chibcha.plus.entity.Cliente;
import com.chibcha.plus.entity.Distribuidor;
import com.chibcha.plus.entity.Empleado;
import com.chibcha.plus.entity.Empleado_comisiones;
import com.chibcha.plus.entity.Empleado_soporte;
import com.chibcha.plus.entity.Usuario;
import com.chibcha.plus.entity.views.VENTAS_DISTRIBUIDOR_ANUAL;
import com.chibcha.plus.entity.views.VENTAS_DISTRIBUIDOR_ANUAL_REPOSITORY;
import com.chibcha.plus.entity.views.VENTAS_DISTRIBUIDOR_MENSUAL;
import com.chibcha.plus.entity.views.VENTAS_DISTRIBUIDOR_MENSUAL_REPOSITORY;
import com.chibcha.plus.entity.views.VENTAS_DISTRIBUIDOR_SEMESTRAL;
import com.chibcha.plus.entity.views.VENTAS_DISTRIBUIDOR_SEMESTRAL_REPOSITORY;
import com.chibcha.plus.repository.AuthorityRepository;
import com.chibcha.plus.repository.UsuarioRepository;
import com.chibcha.plus.service.api.ClienteServiceAPI;
import com.chibcha.plus.service.api.DistribuidorServiceAPI;
import com.chibcha.plus.service.api.Empleado_comisionesServiceAPI;
import com.chibcha.plus.service.api.Empleado_soporteServiceAPI;

@Controller
public class AppController 
{
	
	@Autowired
	private DistribuidorServiceAPI distribuidorServiceAPI;
	
	@Autowired 
	private ClienteServiceAPI clienteServiceAPI;
	
	@Autowired
	private Empleado_soporteServiceAPI soporteServiceAPI;
	
  	@Autowired
  	private Empleado_comisionesServiceAPI comisionesServiceAPI;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private UsuarioRepository userRepository;
	
	@Autowired
	private VENTAS_DISTRIBUIDOR_ANUAL_REPOSITORY ventasAnualRepository;
	
	@Autowired
	private VENTAS_DISTRIBUIDOR_MENSUAL_REPOSITORY ventasMensualRepository;
	
	@Autowired
	private VENTAS_DISTRIBUIDOR_SEMESTRAL_REPOSITORY ventasSemestralRepository;
	
	
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
	public String admin(Model model) 
	{
		int[] datos = new int[4];
		datos[0] = comisionesServiceAPI.listar().size() + soporteServiceAPI.listar().size();
		datos[1] = clienteServiceAPI.listar().size();
		datos[2] = distribuidorServiceAPI.listar().size();
		
		model.addAttribute("datos", datos);
		return "admin";
	}
	
	@GetMapping({"/user"})
	public String user() 
	{
		return "user";
	}
	
	@GetMapping({"/comision"})
	public String comision(Model model) 
	{
		model.addAttribute("distribuidoresList", distribuidorServiceAPI.listar());
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
	
	@GetMapping({"/carousel"})
	public String carousel() 
	{
		return "carousel";
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
	public String admin_clientes(Model model) 
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
	public String crear_distribuidor(Model model, @Valid Distribuidor distribuidor, BindingResult result)
	{
		if(result.hasErrors())
		{
			model.addAttribute("distribuidor", distribuidor);
			model.addAttribute("distribuidoresList", distribuidorServiceAPI.listar());
			return "admin_distribuidores";
		}
		
		
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
	public String crear_cliente(Model model, @Valid Cliente cliente, BindingResult result)
	{
		if(result.hasErrors())
		{
			model.addAttribute("cliente", cliente);
			model.addAttribute("clientesList", clienteServiceAPI.listar());
			Authority userRole = authorityRepository.findByAuthority("ROLE_USER");
			List<Authority> roles = Arrays.asList(userRole);
			model.addAttribute("roles", roles);
			
			return "admin_clientes";
		}
		
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
	public String admin_empleados(Model model) 
	{

		Authority userRole1 = authorityRepository.findByAuthority("ROLE_COMISIONES");
		Authority userRole2 = authorityRepository.findByAuthority("ROLE_SOPORTE");
		
		List<Authority> roles = Arrays.asList(userRole1, userRole2);
		
		model.addAttribute("empleadito", new Empleado());
		model.addAttribute("comisionesList", comisionesServiceAPI.listar());
		model.addAttribute("soporteList", soporteServiceAPI.listar());
		model.addAttribute("roles", roles);
		
		
		return "admin_empleados";
	}
	
	@PostMapping({"/crear_empleado"})
	public String crear_empleado(Model model, @Valid @ModelAttribute("empleadito") Empleado empleadito, BindingResult result)
	{
		if(result.hasErrors())
		{
			model.addAttribute("empleadito", empleadito);
			model.addAttribute("comisionesList", comisionesServiceAPI.listar());
			model.addAttribute("soporteList", soporteServiceAPI.listar());
			
			Authority userRole1 = authorityRepository.findByAuthority("ROLE_COMISIONES");
			Authority userRole2 = authorityRepository.findByAuthority("ROLE_SOPORTE");
			List<Authority> roles = Arrays.asList(userRole1, userRole2);
			model.addAttribute("roles", roles);
			
			return "admin_empleados";
		}
		
		Usuario cif = empleadito.getUsuario();
		if(cif.getPassword().length()<9)
		{
			String passCif = pass.cifrar(cif.getPassword());
			cif.setPassword(passCif);
			empleadito.setUsuario(cif);
		}
		
		if(referencia.isEmpty()==false)
		{
			Optional<Usuario> appUser =  userRepository.findByUsername(referencia);
			if(appUser.isPresent()==true)
			{
				Usuario usuEdit = empleadito.getUsuario();
				usuEdit.setId(appUser.get().getId());
				empleadito.setUsuario(usuEdit);
			}
			
			referencia = "";
		}
		
		if(empleadito.getUsuario().getAuthority().contains(authorityRepository.findByAuthority("ROLE_COMISIONES")))
		{
			Empleado_comisiones empComisiones = new Empleado_comisiones();
			empComisiones.setId(empleadito.getId());
			empComisiones.setNombre(empleadito.getNombre());
			empComisiones.setSueldo(empleadito.getSueldo());
			empComisiones.setUsuario(empleadito.getUsuario());
			comisionesServiceAPI.guardar(empComisiones);
		}
		else if(empleadito.getUsuario().getAuthority().contains(authorityRepository.findByAuthority("ROLE_SOPORTE")))
		{
			Empleado_soporte empSoporte = new Empleado_soporte();
			empSoporte.setId(empleadito.getId());
			empSoporte.setNombre(empleadito.getNombre());
			empSoporte.setSueldo(empleadito.getSueldo());
			empSoporte.setUsuario(empleadito.getUsuario());
			soporteServiceAPI.guardar(empSoporte);
			
		}
		
		model.addAttribute("empleadito", new Empleado());
		model.addAttribute("comisionesList", comisionesServiceAPI.listar());
		model.addAttribute("soporteList", soporteServiceAPI.listar());
		
		Authority userRole1 = authorityRepository.findByAuthority("ROLE_COMISIONES");
		Authority userRole2 = authorityRepository.findByAuthority("ROLE_SOPORTE");
		List<Authority> roles = Arrays.asList(userRole1, userRole2);
		model.addAttribute("roles", roles);
		
		return "admin_empleados";
	}
	
	@GetMapping({"/editar_empleado/{id}/{username}"})
	public String editar_empleado(Model model, @PathVariable(name="id") Long id, @PathVariable(name="username") String username)
	{
		Usuario usuario = userRepository.findByUsername(username).get();
		
		if(usuario.getAuthority().contains(authorityRepository.findByAuthority("ROLE_COMISIONES")))
		{
			Empleado_comisiones empViejo = comisionesServiceAPI.obtener(id);
			
			referencia = empViejo.getUsuario().getUsername();
				
			Empleado empleado = new Empleado();
			empleado.setId(id);
			empleado.setUsuario(usuario);
			empleado.setNombre(empViejo.getNombre());
			empleado.setSueldo(empViejo.getSueldo());
			
			model.addAttribute("empleadito", empleado);			
		}
		
		else if(usuario.getAuthority().contains(authorityRepository.findByAuthority("ROLE_SOPORTE")))
		{
			Empleado_soporte empViejo = soporteServiceAPI.obtener(id);
			
			referencia = empViejo.getUsuario().getUsername();
				
			Empleado empleado = new Empleado();
			empleado.setId(id);
			empleado.setUsuario(usuario);
			empleado.setNombre(empViejo.getNombre());
			empleado.setSueldo(empViejo.getSueldo());
			
			model.addAttribute("empleadito", empleado);	
		}

		model.addAttribute("comisionesList", comisionesServiceAPI.listar());
		model.addAttribute("soporteList", soporteServiceAPI.listar());
		
		Authority userRole1 = authorityRepository.findByAuthority("ROLE_COMISIONES");
		Authority userRole2 = authorityRepository.findByAuthority("ROLE_SOPORTE");
		List<Authority> roles = Arrays.asList(userRole1, userRole2);
		model.addAttribute("roles", roles);
		
		return "admin_empleados";
	}
	
	@GetMapping({"/comision_reporte"})
	public String comision_reporte(Model model) 
	{
		return "comision_reporte";
	}
	
	@GetMapping({"/comision_anual/{id}/{nombre}"})
	public String comision_anual(Model model, @PathVariable(name="nombre") String nombre, @PathVariable(name="id") Long id)
	{		
		List<VENTAS_DISTRIBUIDOR_ANUAL> list = new ArrayList<>();
		ventasAnualRepository.findAllByNombre(nombre).forEach(obj -> list.add(obj));
		model.addAttribute("ventasLista", list);
		
		int[] total = new int[1];
		list.forEach(obj -> total[0]+=obj.getValor());
		model.addAttribute("total", total);
		
		Distribuidor distribuidor = distribuidorServiceAPI.obtener(id);
		model.addAttribute("distribuidor", distribuidor);
		
		
		return "comision_reporte";
	}
	
	@GetMapping({"/comision_mensual/{id}/{nombre}"})
	public String comision_mensual(Model model, @PathVariable(name="nombre") String nombre, @PathVariable(name="id") Long id)
	{		
		List<VENTAS_DISTRIBUIDOR_MENSUAL> list = new ArrayList<>();
		ventasMensualRepository.findAllByNombre(nombre).forEach(obj -> list.add(obj));
		model.addAttribute("ventasLista", list);
		
		int[] total = new int[1];
		list.forEach(obj -> total[0]+=obj.getValor());
		model.addAttribute("total", total);
		
		Distribuidor distribuidor = distribuidorServiceAPI.obtener(id);
		model.addAttribute("distribuidor", distribuidor);
		
		
		return "comision_reporte";
	}
	
	@GetMapping({"/comision_semestral/{id}/{nombre}"})
	public String comision_semestral(Model model, @PathVariable(name="nombre") String nombre, @PathVariable(name="id") Long id)
	{		
		List<VENTAS_DISTRIBUIDOR_SEMESTRAL> list = new ArrayList<>();
		ventasSemestralRepository.findAllByNombre(nombre).forEach(obj -> list.add(obj));
		model.addAttribute("ventasLista", list);
		
		int[] total = new int[1];
		list.forEach(obj -> total[0]+=obj.getValor());
		model.addAttribute("total", total);
		
		Distribuidor distribuidor = distribuidorServiceAPI.obtener(id);
		model.addAttribute("distribuidor", distribuidor);
		
		
		return "comision_reporte";
	}
	
	
	
	
	

}
