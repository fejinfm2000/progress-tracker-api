package com.fm.progresstracker.mapper;

import com.fm.progresstracker.dto.VisitorDto;
import com.fm.progresstracker.entity.Visitor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface CommonMapper {
    CommonMapper INSTENCE =Mappers.getMapper(CommonMapper.class);

    Visitor toViositorEntity(VisitorDto visitorDto);

    VisitorDto toViositorDto(Visitor visitor);
    List<VisitorDto> toViositorList(List<Visitor> visitorList);
}
