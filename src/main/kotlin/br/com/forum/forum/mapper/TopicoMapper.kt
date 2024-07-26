package br.com.forum.forum.mapper

import br.com.forum.forum.dto.TopicoView
import br.com.forum.forum.model.Topico
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy


@Mapper(
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING
)
 interface TopicoMapper {

    fun toDomainTopicoView(topico: Topico): TopicoView

}
