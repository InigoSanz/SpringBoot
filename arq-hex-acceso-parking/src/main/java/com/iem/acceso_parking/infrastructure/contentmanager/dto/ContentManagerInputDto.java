package com.iem.acceso_parking.infrastructure.contentmanager.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentManagerInputDto {
	
	private byte[] archivo;
}