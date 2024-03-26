package com.consult.analysis.service.analysis;

import com.consult.analysis.calculate.AnalysisApi;
import com.consult.analysis.service.event.EventMessage;

/**
 * @author : laoa
 * @describe : 链路器
 * @email : laoa@markcoin.net
 */
public abstract class AnalysisHandler {
    protected AnalysisHandler next;

    protected AnalysisApi api;

    public AnalysisHandler next(AnalysisHandler next){
        this.next=next;
        return this;
    }
    public void doHandler(EventMessage message){
        Boolean result = execute(message);
        if(result==true&&next!=null){
            next.doHandler(message);
        }
    }
    protected abstract Boolean execute(EventMessage message);

    public static class Builder{
        private AnalysisHandler head;
        private AnalysisHandler tail;
        private AnalysisApi analysisApi;
        public Builder(AnalysisApi analysisApi) {
            this.analysisApi=analysisApi;
        }

        public Builder addHandler(AnalysisHandler handler) {
            handler.api=analysisApi;
            if (this.head == null) {
                this.head = this.tail = handler;
                return this;
            }
            this.tail.next(handler);
            this.tail = handler;
            return this;
        }
        public AnalysisHandler build() {
            return this.head;
        }
    }

}
