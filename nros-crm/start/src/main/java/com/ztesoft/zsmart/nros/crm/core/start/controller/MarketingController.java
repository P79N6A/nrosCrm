package com.ztesoft.zsmart.nros.crm.core.start.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;

import com.github.pagehelper.PageInfo;
import com.ztesoft.zsmart.nros.base.annotation.SessionController;
import com.ztesoft.zsmart.nros.base.exception.BusiException;
import com.ztesoft.zsmart.nros.base.model.ResponseMsg;
import com.ztesoft.zsmart.nros.base.util.CommonFunctions;
import com.ztesoft.zsmart.nros.base.util.ConvertUtil;
import com.ztesoft.zsmart.nros.crm.core.client.api.MarketingService;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.EventTriggerHistoryDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingDefineDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.dto.MarketingEventStatisticsDTO;
import com.ztesoft.zsmart.nros.crm.core.client.model.param.MarketingDefineParam;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.EventTriggerHistoryQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineListQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.query.MarketingDefineQuery;
import com.ztesoft.zsmart.nros.crm.core.client.model.vo.MarketingDefineVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 营销管理
 *
 * @author wangzhe
 * @date 2019/4/12 14:24
 */
@SessionController
@RequestMapping("/marketing")
@Api(value = "营销管理", tags = {
    "营销管理"
})
public class MarketingController {

    /**
     * 优惠券服务
     */
    @Autowired
    private MarketingService marketingService;

    /**
     * 营销定义保存
     *
     * @param marketingDefineParam 营销定义保存参数
     * @return 保存结果
     */
    @PostMapping("/define/save")
    @ApiOperation("营销定义保存")
    public ResponseMsg defineSave(@RequestBody MarketingDefineParam marketingDefineParam) {
        Long id = marketingService.defineSave(marketingDefineParam);
        return new ResponseMsg().setData(id).success();
    }

    /**
     * 营销定义修改
     *
     * @param marketingDefineParam 营销定义修改参数
     * @return 修改结果
     */
    @PostMapping("/define/update")
    @ApiOperation("营销定义修改")
    public ResponseMsg defineUpdate(@RequestBody MarketingDefineParam marketingDefineParam) {
        marketingService.defineUpdate(marketingDefineParam);
        return new ResponseMsg().success();
    }

    /**
     * 营销定义删除
     *
     * @param marketingDefineParam 营销定义删除参数
     * @return 删除结果
     */
    @PostMapping("/define/delete")
    @ApiOperation("营销定义删除")
    public ResponseMsg defineDelete(@RequestBody MarketingDefineParam marketingDefineParam) {
        marketingService.defineDelete(marketingDefineParam);
        return new ResponseMsg().success();
    }

    /**
     * 营销流程保存
     *
     * @param marketingDefineParam 营销流程保存参数
     * @return 保存结果
     */
    @PostMapping("/process/save")
    @ApiOperation("营销流程保存")
    public ResponseMsg processSave(@RequestBody MarketingDefineParam marketingDefineParam) {
        marketingService.processSave(marketingDefineParam);
        return new ResponseMsg().success();
    }

    /**
     * 营销流程启用
     *
     * @param marketingDefineParam 营销流程启用参数
     * @return 保存结果
     */
    @PostMapping("/process/enable")
    @ApiOperation("营销流程启用")
    public ResponseMsg processEnable(@RequestBody MarketingDefineParam marketingDefineParam) {
        marketingService.processEnable(marketingDefineParam);
        return new ResponseMsg().success();
    }

    /**
     * 营销流程停用
     *
     * @param marketingDefineParam 营销流程停用参数
     * @return 停用结果
     */
    @PostMapping("/process/disable")
    @ApiOperation("营销流程停用")
    public ResponseMsg processDisable(@RequestBody MarketingDefineParam marketingDefineParam) {
        marketingService.processDisable(marketingDefineParam);
        return new ResponseMsg().success();
    }

    /**
     * 营销定义详情查询
     *
     * @param marketingDefineQuery 营销定义详情查询参数
     */
    @PostMapping("/define/detail")
    @ApiOperation("营销定义详情查询")
    public ResponseMsg<MarketingDefineVO> detail(@RequestBody MarketingDefineQuery marketingDefineQuery) {
        MarketingDefineDTO marketingDefineDTO = marketingService.detail(marketingDefineQuery);
        MarketingDefineVO marketingDefineVO = ConvertUtil.beanCopy(marketingDefineDTO, MarketingDefineVO.class);
        marketingDefineVO.setMarketingConfigJson(JSON.parseObject(marketingDefineDTO.getMarketingConfigJson()));
        ResponseMsg<MarketingDefineVO> responseMsg = new ResponseMsg<>();
        responseMsg.setData(marketingDefineVO);
        return responseMsg.success();
    }

    @PostMapping("/define/query-list")
    @ApiOperation("营销定义列表查询")
    public ResponseMsg queryList(@RequestBody MarketingDefineListQuery query) {
        // return CommonFunctions.runSupplierByPage(() -> this.marketingService.queryList(query), "营销定义列表查询失败");
        ResponseMsg<List<MarketingDefineVO>> responseMsg = new ResponseMsg<>();
        try {
            PageInfo pageInfo = this.marketingService.queryList(query);
            // DTO转VO
            List<MarketingDefineVO> marketingDefineVOList = ConvertUtil.listEntity2DTO(pageInfo.getList(),
                MarketingDefineVO.class);
            pageInfo.setList(marketingDefineVOList);
            responseMsg.success().setData(pageInfo.getList()).setPageIndex(pageInfo.getPageNum())
                .setPageSize(pageInfo.getPageSize()).setPages((long) pageInfo.getPages()).setTotal(pageInfo.getTotal());
        }
        catch (BusiException e) {
            responseMsg.fail().setCode(e.getErrorCode()).setMessage(e.getErrorMsg());
        }
        return responseMsg;
    }

    /**
     * 营销分析周期或有效期和触发频率设置
     *
     * @param param
     * @return
     */
    @PostMapping("/define/set")
    @ApiOperation("营销分析周期或有效期和触发频率设置")
    public ResponseMsg set(@RequestBody MarketingDefineParam param) {
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.success();
        try {
            // TODO 中心无状态，不处理session，暂时屏蔽
            // CommUtils.setBasicInfo(param);
            marketingService.setAnalysis(param);
        }
        catch (BusiException e) {
            responseMsg.setSuccess(false);
            responseMsg.setCode(e.getErrorCode());
            responseMsg.setMessage(e.getMessage());
        }
        return responseMsg;
    }

    @PostMapping("/define/statistics-query")
    @ApiOperation("事件触发统计分析查询")
    public ResponseMsg statisticsQuery(@RequestBody EventTriggerHistoryQuery query) {
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.success();
        try {
            MarketingEventStatisticsDTO marketingEventStatisticsDTO = marketingService.statisticsQuery(query);
            responseMsg.setData(marketingEventStatisticsDTO);
        }
        catch (BusiException e) {
            responseMsg.setSuccess(false);
            responseMsg.setCode(e.getErrorCode());
            responseMsg.setMessage(e.getMessage());
        }
        return responseMsg;
    }

    @PostMapping("/define/event-trigger-list")
    @ApiOperation("事件触发历史列表查询")
    public ResponseMsg eventTriggerList(@RequestBody EventTriggerHistoryQuery query) {
        ResponseMsg<List<EventTriggerHistoryDTO>> responseMsg = new ResponseMsg<>();
        try {
            PageInfo<EventTriggerHistoryDTO> pageInfo = this.marketingService.eventTriggerList(query);
            responseMsg.success().setData(pageInfo.getList()).setPageIndex(pageInfo.getPageNum())
                .setPageSize(pageInfo.getPageSize()).setPages((long) pageInfo.getPages()).setTotal(pageInfo.getTotal());
        }
        catch (BusiException e) {
            responseMsg.fail().setCode(e.getErrorCode()).setMessage(e.getErrorMsg());
        }
        return responseMsg;
    }

    @GetMapping("/define/instance-query")
    @ApiOperation("营销流程执行实例查询")
    public ResponseMsg instanceQuery(@RequestParam("marketingDefineId") Long marketingDefineId) {
        return CommonFunctions.runSupplierByList(() -> this.marketingService.instanceQuery(marketingDefineId),
            "营销流程执行实例查询异常");
    }

    @GetMapping("/define/instance-statistics-query")
    @ApiOperation("营销流程执行实例统计分析查询")
    public ResponseMsg instanceStatisticsQuery(@RequestParam("marketingDefineId") Long marketingDefineId,
        @RequestParam("marketingInstanceId") String marketingInstanceId) {
        return CommonFunctions.runSupplierByList(
            () -> this.marketingService.instanceStatisticsQuery(marketingDefineId, marketingInstanceId),
            "营销流程执行实例统计分析查询异常");
    }

}
