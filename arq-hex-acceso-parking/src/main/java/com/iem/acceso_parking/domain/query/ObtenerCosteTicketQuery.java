package com.iem.acceso_parking.domain.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ObtenerCosteTicketQuery {
	
	private String id;
}