package com.ztesoft.zsmart.nros.crm.core.start.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.base.util.CommonFunctions;
import com.ztesoft.zsmart.nros.crm.core.client.api.EventTraceService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTraceListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTraceTestDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.GrowthRecordDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.PullDownListDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTraceQuery;
import com.ztesoft.zsmart.nros.crm.core.server.middleware.rpc.feigin.proxy.param.GrowthRecordQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员动态轨迹Controller
 *
 * @author litao
 * @date 2019/6/12
 */
@SessionController
@RequestMapping("/trendTrail")
@Api(value = "会员动态轨迹", tags = "会员动态轨迹")
@Slf4j
public class EventTraceController {
    @Autowired
    private EventTraceService eventTraceService;

    @PostMapping("/listTrendTrail")
    @ApiOperation("会员动态轨迹列表查询")
    public ResponseMsg<PageInfo<EventTraceTestDTO>> listTrendTrail(@RequestBody EventTraceQuery eventTraceQuery) {
        log.info("start to TrendTrailController listTrendTrail...");
        if (null == eventTraceQuery.getMemberId()) {
            return new ResponseMsg().fail();
        }
        if (StringUtils.isBlank(eventTraceQuery.getLang())) {
            eventTraceQuery.setLang("zh");
        }
        PageInfo<EventTraceListDTO> eventTraceListDTOPageInfo = eventTraceService.listTrendTrail(eventTraceQuery);
        List<EventTraceListDTO> list = eventTraceListDTOPageInfo.getList();
        List<EventTraceTestDTO> listTest = new ArrayList();
        for (EventTraceListDTO et : list) {
            EventTraceTestDTO eventTraceTestDTO = new EventTraceTestDTO();
            eventTraceTestDTO.setMemberId(et.getMemberId());
            eventTraceTestDTO.setLang(et.getLang());
            eventTraceTestDTO.setEventId(et.getEventId());
            eventTraceTestDTO.setContent(JSONObject.parseObject(et.getContent()));
            eventTraceTestDTO.setGmtCreate(et.getGmtCreate());
            listTest.add(eventTraceTestDTO);
        }
        ResponseMsg eventTraces = CommonFunctions.runSupplierByPage(() -> eventTraceListDTOPageInfo, "查询会员动态轨迹列表失败");
        eventTraces.setData(listTest);
        return eventTraces;
    }

    @GetMapping("/listEventNameCode")
    @ApiOperation("获取事件种类下拉列表")
    public ResponseMsg<List<PullDownListDTO>> listEventNameCode() {
        return new ResponseMsg<List<PullDownListDTO>>().setData(eventTraceService.listEventNameCode()).success();
    }

    @PostMapping("/queryGrowthRecordList")
    @ApiOperation("查询成长值记录")
    public ResponseMsg<List<GrowthRecordDTO>> queryGrowthRecordList(@RequestBody GrowthRecordQuery query) {
        ResponseMsg<List<GrowthRecordDTO>> responseMsg = new ResponseMsg<>();

        EventTraceQuery eventTraceQuery = new EventTraceQuery();
        eventTraceQuery.setMemberId(query.getMemberId());
        eventTraceQuery.setPageIndex(query.getPageIndex());
        eventTraceQuery.setPageSize(query.getPageSize());
        eventTraceQuery.setLang("zh");

        List<GrowthRecordDTO> list = new ArrayList<>();
        GrowthRecordDTO growthRecordDTO;
        PageInfo<EventTraceListDTO> pageInfo = eventTraceService.queryGrowthRecordList(eventTraceQuery);
        if (pageInfo != null) {
            List<EventTraceListDTO> eventTraceListDTOList = pageInfo.getList();
            if (!CollectionUtils.isEmpty(eventTraceListDTOList)) {
                for (EventTraceListDTO eventTraceListDTO : eventTraceListDTOList) {
                    JSONObject jsonObject = JSONObject.parseObject(eventTraceListDTO.getContent());
                    growthRecordDTO = new GrowthRecordDTO();
                    growthRecordDTO.setMemberId(eventTraceListDTO.getMemberId());
                    growthRecordDTO.setGrowth(jsonObject.getInteger("growthCount"));
                    growthRecordDTO.setBizOrder(jsonObject.getString("bizNo"));
                    growthRecordDTO.setGmtModified(eventTraceListDTO.getGmtCreate());
                    if (eventTraceListDTO.getEventId().longValue() == 5L) {
                        growthRecordDTO.setGrowthType("Add");
                    }
                    else {
                        growthRecordDTO.setGrowthType("Sub");
                    }
                    list.add(growthRecordDTO);
                }
            }
        }
        responseMsg.setData(list).success();
        responseMsg.setPageIndex(pageInfo.getPageNum());
        responseMsg.setPageSize(pageInfo.getPageSize());
        responseMsg.setTotal(pageInfo.getTotal());
        return responseMsg;
    }

}
