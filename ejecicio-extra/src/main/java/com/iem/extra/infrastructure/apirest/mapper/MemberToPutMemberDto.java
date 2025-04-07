package com.iem.extra.infrastructure.apirest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.iem.extra.domain.model.Member;
import com.iem.extra.infrastructure.apirest.dto.PutMemberDto;

@Mapper(componentModel = "spring")
public interface MemberToPutMemberDto {
	
	@Mapping(target = "id", ignore = true)
	public Member map(PutMemberDto dto);
}