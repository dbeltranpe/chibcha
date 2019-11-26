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
import com.chibcha.plus.entity.Nivel_servicio;
import com.chibcha.plus.entity.Ticket;
import com.chibcha.plus.entity.Usuario;
import com.chibcha.plus.entity.Ventas_distribuidor;
import com.chibcha.plus.entity.views.VENTAS_DISTRIBUIDOR_ANUAL;
import com.chibcha.plus.entity.views.VENTAS_DISTRIBUIDOR_ANUAL_REPOSITORY;
import com.chibcha.plus.entity.views.VENTAS_DISTRIBUIDOR_MENSUAL;
import com.chibcha.plus.entity.views.VENTAS_DISTRIBUIDOR_MENSUAL_REPOSITORY;
import com.chibcha.plus.entity.views.VENTAS_DISTRIBUIDOR_SEMESTRAL;
import com.chibcha.plus.entity.views.VENTAS_DISTRIBUIDOR_SEMESTRAL_REPOSITORY;
import com.chibcha.plus.repository.AuthorityRepository;
import com.chibcha.plus.repository.EstadoRepository;
import com.chibcha.plus.repository.Nivel_servicioRepository;
import com.chibcha.plus.repository.TicketRepository;
import com.chibcha.plus.repository.UsuarioRepository;
import com.chibcha.plus.service.api.ClienteServiceAPI;
import com.chibcha.plus.service.api.DistribuidorServiceAPI;
import com.chibcha.plus.service.api.Empleado_comisionesServiceAPI;
import com.chibcha.plus.service.api.Empleado_soporteServiceAPI;
import com.chibcha.plus.service.api.TicketServiceAPI;
import com.chibcha.plus.service.api.Ventas_distribuidorServiceAPI;
import com.chibcha.plus.service.impl.TicketServiceImpl;

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
  	private Ventas_distribuidorServiceAPI ventasDistribuidorServiceAPI;
  	
  	@Autowired
  	private TicketRepository ticketServiceAPI;
  	
  	@Autowired
  	private EstadoRepository estadoRepository;
  	
  	@Autowired
  	private Nivel_servicioRepository nivelServicioRepository;
	
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
		
		System.out.println("Fecha Distribuidor ----> " + distribuidor.getFechaingreso());
		
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
	
	@GetMapping({"/comision_ventas_distribuidor"})
	public String comision_ventas_distribuidor(Model model) 
	{
		model.addAttribute("ventaDistribuidor", new Ventas_distribuidor());
		model.addAttribute("distribuidoresList", distribuidorServiceAPI.listar());
		model.addAttribute("ventaList", ventasDistribuidorServiceAPI.listar());
		return "comision_ventas_distribuidor";
	}
	
	@PostMapping({"/crear_venta_distribuidor"})
	public String crear_venta_distribuidor(Model model, @Valid Ventas_distribuidor ventaDistribuidor, BindingResult result)
	{
		if(result.hasErrors())
		{
			model.addAttribute("ventaDistribuidor", ventaDistribuidor);
			model.addAttribute("distribuidoresList", distribuidorServiceAPI.listar());
			return "comision_ventas_distribuidor";
		}
		
		System.out.println(ventaDistribuidor.getFecha());
		
		ventasDistribuidorServiceAPI.guardar(ventaDistribuidor);
		model.addAttribute("ventaDistribuidor", new Ventas_distribuidor());
		model.addAttribute("distribuidoresList", distribuidorServiceAPI.listar());
		
		return "comision_ventas_distribuidor";
	}
	
	@GetMapping({"/gestion_tickets"})
	public String gestion_tickets(Model model) 
	{
		model.addAttribute("ticket", new Ticket());
		return "gestion_tickets";
	}
	
	@PostMapping({"/crear_ticket"})
	public String crear_ticket(Model model, @Valid Ticket ticket, BindingResult result)
	{
		if(result.hasErrors())
		{
			model.addAttribute("ticket", new Ticket());
			return "gestion_tickets";
		}
		
		ticketServiceAPI.save(ticket);
		model.addAttribute("ticket", new Ticket());
		return "gestion_tickets";
	}
	
	@GetMapping({"/soporte"})
	public String soporte(Model model) 
	{
		model.addAttribute("nivelServicioList", nivelServicioRepository.findAll());
		model.addAttribute("ticketsList", ticketServiceAPI.listarSinClasificar());
		return "soporte";
	}
	
	@GetMapping({"/clasificar_ticket/{id}/{idNivelServicio}"})
	public String clasificar_ticket(Model model, @PathVariable(name="id") Long id, @PathVariable(name="idNivelServicio") Long idNivelServicio) 
	{
		Ticket tick = ticketServiceAPI.findById(id).get();
		tick.setNivelServicio(nivelServicioRepository.findById(idNivelServicio).get());
		ticketServiceAPI.save(tick);
		
		model.addAttribute("nivelServicioList", nivelServicioRepository.findAll());
		model.addAttribute("ticketsList", ticketServiceAPI.listarSinClasificar());
		return "soporte";
	}
	
	@GetMapping({"/soporte_ns_urgente"})
	public String soporte_ns_urgente(Model model) 
	{
		model.addAttribute("ticketsSinAtender", ticketServiceAPI.listarUrgenteSinAtender());
		model.addAttribute("ticketsEnAtencion", ticketServiceAPI.listarUrgenteEnAtencion());
		model.addAttribute("ticketsAtendido", ticketServiceAPI.listarUrgenteAtendido());
		return "soporte_ns_urgente";
	}
	
	@GetMapping({"/soporte_ns_urgente_atender/{id}"})
	public String soporte_ns_urgente_atender(Model model, @PathVariable(name="id") Long id) 
	{
		Ticket tick = ticketServiceAPI.findById(id).get();
		Long num = new Long(2);
		tick.setEstado(estadoRepository.findById(num).get());
		ticketServiceAPI.save(tick);
		
		
		model.addAttribute("ticketsSinAtender", ticketServiceAPI.listarUrgenteSinAtender());
		model.addAttribute("ticketsEnAtencion", ticketServiceAPI.listarUrgenteEnAtencion());
		model.addAttribute("ticketsAtendido", ticketServiceAPI.listarUrgenteAtendido());
		return "soporte_ns_urgente";
	}
	
	@GetMapping({"/soporte_ns_urgente_finalizar/{id}"})
	public String soporte_ns_urgente_finalizar(Model model, @PathVariable(name="id") Long id) 
	{
		Ticket tick = ticketServiceAPI.findById(id).get();
		Long num = new Long(3);
		tick.setEstado(estadoRepository.findById(num).get());
		ticketServiceAPI.save(tick);
		
		
		model.addAttribute("ticketsSinAtender", ticketServiceAPI.listarUrgenteSinAtender());
		model.addAttribute("ticketsEnAtencion", ticketServiceAPI.listarUrgenteEnAtencion());
		model.addAttribute("ticketsAtendido", ticketServiceAPI.listarUrgenteAtendido());
		return "soporte_ns_urgente";
	}
	
	@GetMapping({"/soporte_ns_importante"})
	public String soporte_ns_importante(Model model) 
	{
		model.addAttribute("ticketsSinAtender", ticketServiceAPI.listarImportanteSinAtender());
		model.addAttribute("ticketsEnAtencion", ticketServiceAPI.listarImportanteEnAtencion());
		model.addAttribute("ticketsAtendido", ticketServiceAPI.listarImportanteAtendido());
		return "soporte_ns_importante";
	}
	
	@GetMapping({"/soporte_ns_importante_atender/{id}"})
	public String soporte_ns_importante_atender(Model model, @PathVariable(name="id") Long id) 
	{
		Ticket tick = ticketServiceAPI.findById(id).get();
		Long num = new Long(2);
		tick.setEstado(estadoRepository.findById(num).get());
		ticketServiceAPI.save(tick);
		
		
		model.addAttribute("ticketsSinAtender", ticketServiceAPI.listarImportanteSinAtender());
		model.addAttribute("ticketsEnAtencion", ticketServiceAPI.listarImportanteEnAtencion());
		model.addAttribute("ticketsAtendido", ticketServiceAPI.listarImportanteAtendido());
		return "soporte_ns_importante";
	}
	
	@GetMapping({"/soporte_ns_importante_finalizar/{id}"})
	public String soporte_ns_importante_finalizar(Model model, @PathVariable(name="id") Long id) 
	{
		Ticket tick = ticketServiceAPI.findById(id).get();
		Long num = new Long(3);
		tick.setEstado(estadoRepository.findById(num).get());
		ticketServiceAPI.save(tick);
		
		
		model.addAttribute("ticketsSinAtender", ticketServiceAPI.listarImportanteSinAtender());
		model.addAttribute("ticketsEnAtencion", ticketServiceAPI.listarImportanteEnAtencion());
		model.addAttribute("ticketsAtendido", ticketServiceAPI.listarImportanteAtendido());
		return "soporte_ns_importante";
	}
	
	@GetMapping({"/soporte_ns_normal"})
	public String soporte_ns_normal(Model model) 
	{
		model.addAttribute("ticketsSinAtender", ticketServiceAPI.listarNormalSinAtender());
		model.addAttribute("ticketsEnAtencion", ticketServiceAPI.listarNormalEnAtencion());
		model.addAttribute("ticketsAtendido", ticketServiceAPI.listarNormalAtendido());
		return "soporte_ns_normal";
	}
	
	@GetMapping({"/soporte_ns_normal_atender/{id}"})
	public String soporte_ns_normal_atender(Model model, @PathVariable(name="id") Long id) 
	{
		Ticket tick = ticketServiceAPI.findById(id).get();
		Long num = new Long(2);
		tick.setEstado(estadoRepository.findById(num).get());
		ticketServiceAPI.save(tick);
		
		
		model.addAttribute("ticketsSinAtender", ticketServiceAPI.listarNormalSinAtender());
		model.addAttribute("ticketsEnAtencion", ticketServiceAPI.listarNormalEnAtencion());
		model.addAttribute("ticketsAtendido", ticketServiceAPI.listarNormalAtendido());
		return "soporte_ns_normal";
	}
	
	@GetMapping({"/soporte_ns_normal_finalizar/{id}"})
	public String soporte_ns_normal_finalizar(Model model, @PathVariable(name="id") Long id) 
	{
		Ticket tick = ticketServiceAPI.findById(id).get();
		Long num = new Long(3);
		tick.setEstado(estadoRepository.findById(num).get());
		ticketServiceAPI.save(tick);
		
		
		model.addAttribute("ticketsSinAtender", ticketServiceAPI.listarNormalSinAtender());
		model.addAttribute("ticketsEnAtencion", ticketServiceAPI.listarNormalEnAtencion());
		model.addAttribute("ticketsAtendido", ticketServiceAPI.listarNormalAtendido());
		return "soporte_ns_normal";
	}
	
	

}
