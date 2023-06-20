package org.bill.demoproject.common.typehandler;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.ibatis.type.MappedTypes;
import org.bill.demoproject.beans.OptionEntity;

@MappedTypes({List.class})
public class OptionEntityListTypeHandler extends  ListTypeHandler<OptionEntity> {
    @Override
    protected TypeReference<List<OptionEntity>> specificType() {
        System.out.println("specificType");
        return new TypeReference<List<OptionEntity>>() {
        };
    }
}

