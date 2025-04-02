package com.iem.extra.infrastructure.database.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;

import com.iem.extra.domain.model.Member;
import com.iem.extra.infrastructure.database.entity.MemberEntity;

@Mapper(componentModel = "spring")
public interface MemberToMemberEntityMapper {

	public MemberEntity map(Member member);

	public Member map(MemberEntity member);

	default Optional<Member> map(Optional<MemberEntity> member) {
		return member == null ? null : Optional.ofNullable(map(member.get()));
	}

}