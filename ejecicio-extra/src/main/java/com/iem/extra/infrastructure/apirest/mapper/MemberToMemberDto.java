package com.iem.extra.infrastructure.apirest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.iem.extra.domain.model.Member;
import com.iem.extra.infrastructure.apirest.dto.MemberDto;

@Mapper(componentModel = "spring")
public interface MemberToMemberDto {
	
	@Mapping(target = "id", ignore = true)
	public MemberDto map(Member domain);
}