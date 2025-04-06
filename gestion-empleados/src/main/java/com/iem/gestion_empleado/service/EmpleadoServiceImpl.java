package com.iem.gestion_empleado.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iem.gestion_empleado.apirest.dto.DepartamentoDto;
import com.iem.gestion_empleado.apirest.dto.EmpleadoCrearDto;
import com.iem.gestion_empleado.apirest.dto.EmpleadoDto;
import com.iem.gestion_empleado.database.entity.DepartamentoEntity;
import com.iem.gestion_empleado.database.entity.EmpleadoEntity;
import com.iem.gestion_empleado.database.repository.DepartamentoRepository;
import com.iem.gestion_empleado.database.repository.EmpleadoRepository;
import com.iem.gestion_empleado.mapper.DepartamentoMapper;
import com.iem.gestion_empleado.mapper.EmpleadoMapper;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {
	
	@Autowired
	EmpleadoRepository empleadoRepository;
	
	// Inyectamos el departamento para comprobar que existe
	@Autowired
	DepartamentoRepository departamentoRepository;
	
	@Autowired
	EmpleadoMapper empleadoMapper;
	
	// Inyectamos el mapper de los departamentos para los métodos que requieren trabajar con departamentos
	@Autowired
	DepartamentoMapper departamentoMapper;
	
	@Override
	public List<EmpleadoDto> obtenerTodosEmpleados() {
		
		List<EmpleadoEntity> empleados = empleadoRepository.findAll();
		
		return empleadoMapper.empleadoEntityListToDtoList(empleados);
	}

	@Override
	public EmpleadoDto crearEmpleado(EmpleadoCrearDto dto) {
		
		EmpleadoEntity entidad = empleadoMapper.empleadoCrearDtoToEntity(dto);
		EmpleadoEntity guardar = empleadoRepository.save(entidad);
		
		return empleadoMapper.empleadoEntityToDto(guardar);
	}

	@Override
	public EmpleadoDto obtenerEmpleadoPorId(String id) {
		
		Optional<EmpleadoEntity> empleado = empleadoRepository.findById(id);
		
		if (empleado.isPresent()) {
			return empleadoMapper.empleadoEntityToDto(empleado.get());
		} else {
			return null;
		}
	}

	@Override
	public EmpleadoDto actualizarEmpleado(String id, EmpleadoCrearDto empleadoDto) {
		
		Optional<EmpleadoEntity> empleadoOptional = empleadoRepository.findById(id);
		
		if (empleadoOptional.isPresent()) {
			EmpleadoEntity empleadoEntidad = empleadoOptional.get();
			
			empleadoMapper.actualizarEntityDeDto(empleadoDto, empleadoEntidad);
			
			EmpleadoEntity empleadoGuardar = empleadoRepository.save(empleadoEntidad);
			
			return empleadoMapper.empleadoEntityToDto(empleadoGuardar);
		} else {
			return null;
		}
	}

	@Override
	public boolean eliminarEmpleado(String id) {
		
		Optional<EmpleadoEntity> empleado = empleadoRepository.findById(id);
		
		if (empleado.isPresent()) {
			empleadoRepository.deleteById(id);
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * Intenté hacerlo con map struct, pero me lié, luego comprendí que al ser una actualización
	 * de un campo ya existente, no es necesario mappear, si no simplemente asinar.
	 */
	@Override
	public boolean asignarDepartamento(String id, String departamentoId) {
		
		Optional<EmpleadoEntity> empleadoOptional = empleadoRepository.findById(id);
		
		if (empleadoOptional.isPresent()) {
			
			// Comprobamos que existe el departamento, en la primera versión no se comprobó
			Optional<DepartamentoEntity> departamentoOptional = departamentoRepository.findById(departamentoId);
			
			if (!departamentoOptional.isPresent()) {
				return false;
			}
			
			EmpleadoEntity empleado = empleadoOptional.get();
			List<String> departamentos = empleado.getDepartamentosIds();
			
			// Comprobamos si no contiene el departamento y lo asignamos
			if (!departamentos.contains(departamentoId)) {
				departamentos.add(departamentoId);
				empleado.setDepartamentosIds(departamentos);
				empleadoRepository.save(empleado);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public boolean eliminarDepartamento(String id, String departamentoId) {
		
		Optional<EmpleadoEntity> empleadoOptional = empleadoRepository.findById(id);
		
		// ¿Hay que comprobar que el departamento existe en eta ocasión?
		if (empleadoOptional.isPresent()) {
			EmpleadoEntity empleado = empleadoOptional.get();
			List<String> departamentos = empleado.getDepartamentosIds();
			
			if (departamentos.contains(departamentoId)) {
				departamentos.remove(departamentoId);
				empleado.setDepartamentosIds(departamentos);
				empleadoRepository.save(empleado);
			}
			
			return true;
		}
		
		return false;
	}

	@Override
	public List<DepartamentoDto> obtenerDepartamentosDeEmpleado(String id) {
		
		Optional<EmpleadoEntity> empleadoOptional = empleadoRepository.findById(id);
		
		if (empleadoOptional.isPresent()) {
			EmpleadoEntity empleado = empleadoOptional.get();
			List<String> idsDepartamentos = empleado.getDepartamentosIds();
			
			List<DepartamentoEntity> departamentos = departamentoRepository.findAllById(idsDepartamentos);
			
			return departamentoMapper.departamentoEntityListToDtoList(departamentos);
		}
		
		return new ArrayList<>(); // Sonar dice que es mejor devolver una lista vacía, investigamos el motivo
								  // Tenemos que cambiar el controlador
	}

	@Override
	public DepartamentoDto obtenerDepartamentoDeEmpleado(String id, String departamentoId) {
		
		Optional<EmpleadoEntity> empleadoOptional = empleadoRepository.findById(id);
		
		if (empleadoOptional.isPresent()) {
			EmpleadoEntity empleado = empleadoOptional.get();
			List<String> departamentos = empleado.getDepartamentosIds();
			
			if(departamentos.contains(departamentoId)) {
				// Obtenemos el departamento
				Optional<DepartamentoEntity> departamentoOptional = departamentoRepository.findById(departamentoId);
				
				if (departamentoOptional.isPresent()) {
					return departamentoMapper.departamentoEntityToDto(departamentoOptional.get()); // Recordad que si utilizamos optional hay que obtenerlo con .get()
				}
			}
		}
		
		return null;
	}
}