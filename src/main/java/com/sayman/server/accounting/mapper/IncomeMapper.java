package com.sayman.server.accounting.mapper;

import com.sayman.server.accounting.dto.IncomeRequest;
import com.sayman.server.accounting.dto.IncomeResponse;
import com.sayman.server.accounting.model.ExpectedDateRange;
import com.sayman.server.accounting.model.Income;
import com.sayman.server.auth.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class IncomeMapper {

    @Mapping(target = "id", source = "incomeRequest.id")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", source = "user")
    @Mapping(target = "isHappened", source = "incomeRequest.isHappened")
    @Mapping(target = "happenedDate", source = "incomeRequest.happenedDate")
    @Mapping(target = "expectedDateRange", expression = "java(generateExpectedDateRange(incomeRequest))")
    @Mapping(target ="isRegular", source = "incomeRequest.isRegular")
    @Mapping(target ="name", source = "incomeRequest.name")
    @Mapping(target ="amount", source = "incomeRequest.amount")
    @Mapping(target ="labelColor", source = "incomeRequest.labelColor")
    public abstract Income map(IncomeRequest incomeRequest, User user);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "isHappened", source = "isHappened")
    @Mapping(target = "happenedDate", source = "happenedDate")
    @Mapping(target = "expectedDateStart", source = "expectedDateRange.start")
    @Mapping(target = "expectedDateEnd", source = "expectedDateRange.end")
    @Mapping(target = "isRegular", source = "isRegular")
    @Mapping(target ="name", source = "name")
    @Mapping(target ="amount", source = "amount")
    @Mapping(target ="labelColor", source = "labelColor")
    public abstract IncomeResponse mapToDto(Income income);

    ExpectedDateRange generateExpectedDateRange(IncomeRequest incomeRequest) {
        return  new ExpectedDateRange(incomeRequest.getExpectedDateStart(), incomeRequest.getExpectedDateEnd());
    }
}
