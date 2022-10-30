package com.demo;

/**
 * <Description> <br>
 *
 * @author shihan@qq.com<br>
 * @version 1.0<br>
 * @date 2022/10/30 <br>
 */

public class CompletionService {
    ExecutorService executor = Executors.newFixedThreadPool(10);
    //查询用户信息
    CompletionService<Object> baseDTOCompletionService = new ExecutorCompletionService<Object>(executor);
    Callable<Object> userInfoDTOCallableTask = () -> {
        UserInfoParam userInfoParam = buildUserParam(req);
        return userService.queryUserInfo(userInfoParam);
    };
    //banner信息任务
    Callable<Object> bannerDTOCallableTask = () -> {
        BannerParam bannerParam = buildBannerParam(req);
        return bannerService.queryBannerInfo(bannerParam);
    };

//提交用户信息任务
baseDTOCompletionService.submit(userInfoDTOCallableTask);
//提交banner信息任务
baseDTOCompletionService.submit(bannerDTOCallableTask);
}
