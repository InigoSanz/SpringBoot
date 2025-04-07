package com.iem.acceso_parking.domain.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagarTicketCommand {
	
	private String id;
}