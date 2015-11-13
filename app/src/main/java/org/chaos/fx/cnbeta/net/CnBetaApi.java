package org.chaos.fx.cnbeta.net;

import org.chaos.fx.cnbeta.net.model.ArticleSummary;
import org.chaos.fx.cnbeta.net.model.Comment;
import org.chaos.fx.cnbeta.net.model.HotComment;
import org.chaos.fx.cnbeta.net.model.NewsContent;
import org.chaos.fx.cnbeta.net.model.Topic;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * @author Chaos
 *         2015/11/01.
 */
public interface CnBetaApi {

    String BASE_URL = "http://api.cnbeta.com";
    String BASE_PARAMS = "/capi?app_key=10000&format=json&v=1.0&mpuffgvbvbttn3Rc&method=";

    /**
     * 评论最热
     */
    int TYPE_COMMENTS = 1;

    /**
     * 阅读最多
     */
    int TYPE_COUNTER = 2;

    /**
     * 最高推荐
     */
    int TYPE_DIG = 3;

    /**
     * 文章列表 api，一次返回 20 条
     *
     * @param timestamp 时间戳
     * @param sign      规定顺序的字符串加密后的结果，参考 {@link CnBetaSignUtil#articlesSign(long)}
     * @return 成功则返回 status 字符串以及文章简略数据，失败直接崩溃 _(:зゝ∠)_
     */
    @GET(BASE_PARAMS + "Article.Lists")
    Call<Result<List<ArticleSummary>>> articles(@Query("timestamp") long timestamp,
                                                @Query("sign") String sign);

    /**
     * 话题相关文章
     *
     * @param timestamp 时间戳
     * @param sign      加密字符串
     * @param topicId   话题 id
     * @return 文章列表
     */
    @GET(BASE_PARAMS + "Article.Lists")
    Call<Result<List<ArticleSummary>>> topicArticles(@Query("timestamp") long timestamp,
                                                     @Query("sign") String sign,
                                                     @Query("topicid") String topicId);

    /**
     * 最新文章
     *
     * @param timestamp 时间戳
     * @param sign      加密字符串
     * @param topicId   话题 id，无 id 时直接填 null
     * @param startSid  已加载的最新的文章的 id
     * @return 文章列表
     */
    @GET(BASE_PARAMS + "Article.Lists")
    Call<Result<List<ArticleSummary>>> newArticles(@Query("timestamp") long timestamp,
                                                   @Query("sign") String sign,
                                                   @Query("topicid") String topicId,
                                                   @Query("start_sid") String startSid);

    /**
     * 最新文章
     *
     * @param timestamp 时间戳
     * @param sign      加密字符串
     * @param topicId   话题 id，无 id 时直接填 null
     * @param endSid    已加载的最旧的文章的 id
     * @return 文章列表
     */
    @GET(BASE_PARAMS + "Article.Lists")
    Call<Result<List<ArticleSummary>>> oldArticles(@Query("timestamp") long timestamp,
                                                   @Query("sign") String sign,
                                                   @Query("topicid") String topicId,
                                                   @Query("end_sid") String endSid);

    /**
     * 文章详情
     *
     * @param timestamp 时间戳
     * @param sign      加密字符串
     * @param sid       文章 id
     * @return 成功则返回文章详细数据
     */
    @GET(BASE_PARAMS + "Article.NewsContent")
    Call<Result<NewsContent>> articleContent(@Query("timestamp") long timestamp,
                                             @Query("sign") String sign,
                                             @Query("sid") String sid);

    @GET(BASE_PARAMS + "Article.Comment&pageSize=20")
    Call<Result<List<Comment>>> comments(@Query("timestamp") long timestamp,
                                         @Query("sign") String sign,
                                         @Query("sid") String sid,
                                         @Query("page") int page);

    /**
     * 评论
     *
     * @param timestamp 时间戳
     * @param sign      加密字符串
     * @param sid       文章 id
     * @param content   评论内容
     * @return FIXME 未知，目前一直返回评论参数错误（官方也这尿性……）
     */
    @GET(BASE_PARAMS + "Article.DoCmt&op=publish")
    Call<Result<Object>> addComment(@Query("timestamp") long timestamp,
                                    @Query("sign") String sign,
                                    @Query("sid") String sid,
                                    @Query("content") String content);

    /**
     * 回复评论
     *
     * @param timestamp 时间戳
     * @param sign      加密字符串
     * @param sid       文章 id
     * @param pid       对应评论的 id
     * @param content   回复内容
     * @return FIXME 未知，目前一直返回评论参数错误（官方也这尿性……）
     */
    @GET(BASE_PARAMS + "Article.DoCmt&op=publish")
    Call<Result<Object>> replyComment(@Query("timestamp") long timestamp,
                                      @Query("sign") String sign,
                                      @Query("sid") String sid,
                                      @Query("pid") String pid,
                                      @Query("content") String content);

    /**
     * 支持评论
     *
     * @param timestamp 时间戳
     * @param sign      加密串
     * @param sid       评论 id
     * @return 操作状态描述
     */
    @GET(BASE_PARAMS + "Article.DoCmt&op=support&tid=1")
    Call<Result<String>> supportComment(@Query("timestamp") long timestamp,
                                        @Query("sign") String sign,
                                        @Query("sid") String sid);

    /**
     * 反对评论
     *
     * @param timestamp 时间戳
     * @param sign      加密串
     * @param sid       评论 id
     * @return 操作状态描述
     */
    @GET(BASE_PARAMS + "Article.DoCmt&op=against&tid=0")
    Call<Result<String>> againstComment(@Query("timestamp") long timestamp,
                                        @Query("sign") String sign,
                                        @Query("sid") String sid);

    /**
     * 热门评论
     *
     * @param timestamp 时间戳
     * @param sign      加密字符串
     * @return 热门评论列表
     */
    @GET(BASE_PARAMS + "Article.RecommendComment")
    Call<Result<List<HotComment>>> hotComment(@Query("timestamp") long timestamp,
                                              @Query("sign") String sign);

    /**
     * 今日排行
     *
     * @param timestamp 时间戳
     * @param sign      加密字符串
     * @param type      排行类型
     * @return 文章列表
     */
    @GET(BASE_PARAMS + "Article.TodayRank")
    Call<Result<List<ArticleSummary>>> todayRank(@Query("timestamp") long timestamp,
                                                 @Query("sign") String sign,
                                                 @Query("type") String type);

    /**
     * 本月 Top 10
     *
     * @param timestamp 时间戳
     * @param sign      加密字符串
     * @return 成功则返回本月热度最高的10篇文章
     */
    @GET(BASE_PARAMS + "Article.Top10")
    Call<Result<List<ArticleSummary>>> top10(@Query("timestamp") long timestamp,
                                             @Query("sign") String sign);

    /**
     * 文章主题
     *
     * @param timestamp 时间戳
     * @param sign      加密字符串
     * @return 成功则返回文章主题列表
     */
    @GET(BASE_PARAMS + "Article.NavList")
    Call<Result<List<Topic>>> topics(@Query("timestamp") long timestamp,
                                     @Query("sign") String sign);


    class Result<T> {
        public String status;
        public T result;
    }
}