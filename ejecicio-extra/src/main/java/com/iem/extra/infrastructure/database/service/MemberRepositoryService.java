package com.iem.extra.infrastructure.database.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iem.extra.application.port.MemberRepositoryOutputPort;
import com.iem.extra.domain.model.Member;
import com.iem.extra.infrastructure.database.entity.MemberEntity;
import com.iem.extra.infrastructure.database.mapper.MemberToMemberEntityMapper;
import com.iem.extra.infrastructure.database.repository.MemberRepository;

@Component
public class MemberRepositoryService implements MemberRepositoryOutputPort {
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	MemberToMemberEntityMapper memberToMemberEntityMapper;
	
	@Override
	public String crearMiembro(Member member) {
		
		MemberEntity me = memberToMemberEntityMapper.map(member);
		
		MemberEntity nuevoMiembro = memberRepository.save(me);
		
		return nuevoMiembro.getId();
	}

	@Override
	public Optional<Member> obtenerMiembro(String memberId) {
		
		Optional<MemberEntity> member = memberRepository.findById(memberId);
		
		return memberToMemberEntityMapper.map(member);
	}

	@Override
	public void modificarMiembro(Member member) {
		
		memberRepository.save(memberToMemberEntityMapper.map(member));	
	}
}