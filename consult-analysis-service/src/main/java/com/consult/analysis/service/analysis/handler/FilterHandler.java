package com.consult.analysis.service.analysis.handler;

import com.consult.analysis.calculate.provider.model.FilterResult;
import com.consult.analysis.service.analysis.AnalysisHandler;
import com.consult.analysis.service.enums.AnalyzeType;
import com.consult.analysis.service.enums.HandlerStep;
import com.consult.analysis.service.event.EventMessage;

import java.util.stream.Collectors;

/**
 * @author : laoa
 * @describe : 关键字匹配
 * @email : laoa@markcoin.net
 */
public class FilterHandler extends AnalysisHandler {

    @Override
    protected Boolean execute(EventMessage message) {
        if(message.getAnalyzeType().equals(AnalyzeType.SUBSTANTIVE)){
            return true;
        }
        String content = message.getContent();
        content=content.toLowerCase();
        FilterResult match = this.api.filter().match(content);
        if(match.getResult()){
            if(match.getCodes().size()>0){
                message.setCodes(match.getCodes().stream().map(i->i+"").collect(Collectors.joining(",")));
            }
            if(match.getCoins().size()>0){
                message.setCoins(match.getCoins().stream().collect(Collectors.joining(",")));
            }
        }
        return match.getResult();
    }
}
