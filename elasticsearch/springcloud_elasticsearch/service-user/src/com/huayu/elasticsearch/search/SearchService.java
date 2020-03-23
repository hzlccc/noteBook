package com.huayu.elasticsearch.search;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.document.DocumentField;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.PipelineAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.composite.ParsedComposite.ParsedBucket;
import org.elasticsearch.search.aggregations.bucket.histogram.Histogram;
import org.elasticsearch.search.aggregations.bucket.range.RangeAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedDoubleTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.avg.ParsedAvg;
import org.elasticsearch.search.aggregations.metrics.stats.Stats;
import org.elasticsearch.search.aggregations.metrics.sum.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.tophits.TopHits;
import org.elasticsearch.search.aggregations.pipeline.PipelineAggregatorBuilders;
import org.elasticsearch.search.aggregations.pipeline.bucketmetrics.max.MaxBucketPipelineAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import com.huayu.bean.user.Shgx;
import com.huayu.elasticsearch.ElasticSearchUtil;

@Service
public class SearchService {

	public static void termSearch() {
		// TODO Auto-generated method stub
		RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        //sourceBuilder.fetchSource(new String[]{"title"}, new String[]{});
        //MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "费德勒");
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("dAge", "60");
        //RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("publishTime");
        //rangeQueryBuilder.gte("2018-01-26T08:00:00Z");
        //rangeQueryBuilder.lte("2018-01-26T20:00:00Z");
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        //boolBuilder.must(matchQueryBuilder);
        boolBuilder.must(termQueryBuilder);
        //boolBuilder.must(rangeQueryBuilder);
        sourceBuilder.query(boolBuilder);
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.types("type");
        searchRequest.source(sourceBuilder);
        try {
        	System.out.println("====================================================================");
        	System.out.println(searchRequest.source().toString());
			SearchResponse searchResponse = client.search(searchRequest);
			System.out.println(searchResponse);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	}
	
	
	public static void fuzzySearch() {
		// TODO Auto-generated method stub
		RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        
        FuzzyQueryBuilder fuzzyQuery = QueryBuilders.fuzzyQuery("cAddress", "曹县");
        
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.must(fuzzyQuery);
        sourceBuilder.query(boolBuilder);
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.types("type");
        searchRequest.source(sourceBuilder);
        try {
        	System.out.println("====================================================================");
        	System.out.println(searchRequest.source().toString());
			SearchResponse searchResponse = client.search(searchRequest);
			System.out.println(searchResponse);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	}
	public static void matchSearch() {
		// TODO Auto-generated method stub
		RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("cAddress", "青古集");
        
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        
        boolBuilder.must(matchQueryBuilder);
        
        sourceBuilder.query(matchQueryBuilder);
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.types("type");
        searchRequest.source(sourceBuilder);
        try {
        	System.out.println("====================================================================");
        	System.out.println(searchRequest.source().toString());
			SearchResponse searchResponse = client.search(searchRequest);
			System.out.println(searchResponse);
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
	}
	
	
public static void realNameSearch() {
		
		String[] includeFields = null;
		
		String[] excludeFields = {"passWord"};
		
		
		// TODO Auto-generated method stub
		RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.from(0);
        sourceBuilder.size(10);
        
        //sourceBuilder.fetchSource(new String[]{"title"}, new String[]{});
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("realName", "胡智龙");
        //TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("shgx.dAge", "100");
        //RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("publishTime");
        //rangeQueryBuilder.gte("2018-01-26T08:00:00Z");
        //rangeQueryBuilder.lte("2018-01-26T20:00:00Z");
        
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
        boolBuilder.must(matchQueryBuilder);
        //boolBuilder.must(termQueryBuilder);
        //boolBuilder.must(rangeQueryBuilder);
        sourceBuilder.query(boolBuilder);
        sourceBuilder.fetchSource(includeFields, excludeFields);
        
        SearchRequest searchRequest = new SearchRequest("user");
        searchRequest.types("type");
        searchRequest.source(sourceBuilder);
        try {
        	System.out.println("====================================================================");
        	System.out.println(searchRequest.source().toString());
			SearchResponse searchResponse = client.search(searchRequest);
			System.out.println(searchResponse);
        	
			System.out.println("====================================================================");
			SearchHits hits = searchResponse.getHits();
			for (SearchHit searchHit : hits) {
				String source = searchHit.getSourceAsString();
				System.out.println(source);
			}
			System.out.println("====================================================================");
			client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
public static void duoTiaojianSearch() {
	
	String[] includeFields = {"shgx.dAge"};
	
	String[] excludeFields = {"passWord"};
	
	
	RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    
	
    
    MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("realName", "胡智龙");
    //TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("shgx.dAge", "100");
    RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery("shgx.dAge");
    rangeQueryBuilder.gte("50");
    rangeQueryBuilder.lte("100");
    
    BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
    boolBuilder.must(matchQueryBuilder);
    //boolBuilder.must(termQueryBuilder);
    boolBuilder.must(rangeQueryBuilder);
    
    sourceBuilder.from(0);
    sourceBuilder.size(20);
    sourceBuilder.query(boolBuilder);
    sourceBuilder.fetchSource(includeFields, excludeFields);
    
    SearchRequest searchRequest = new SearchRequest("user");
    searchRequest.types("type");
    searchRequest.source(sourceBuilder);
    try {
    	System.out.println("====================================================================");
    	System.out.println(searchRequest.source().toString());
		SearchResponse searchResponse = client.search(searchRequest);
		System.out.println(searchResponse);
    	
		System.out.println("====================================================================");
		SearchHits hits = searchResponse.getHits();
		for (SearchHit searchHit : hits) {
			String source = searchHit.getSourceAsString();
			System.out.println(source);
		}
		System.out.println("====================================================================");
		client.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static void juhe() {
	
	
	RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    
	
    
    //MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("realName", "胡智龙");
    
    BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
   // boolBuilder.must(matchQueryBuilder);
    
    
    TermsAggregationBuilder aggregation = AggregationBuilders.terms("realName")
            .field("realName");
    //计算每组的平均balance指标
    TermsAggregationBuilder subAggregation = aggregation.subAggregation(AggregationBuilders.terms("password")
            .field("passWord"));
    //subAggregation.subAggregation(AggregationBuilders.sum("passWord_avg")).field("passWord");
    
    sourceBuilder.from(0);
    sourceBuilder.size(20);
    sourceBuilder.query(boolBuilder);
    sourceBuilder.aggregation(aggregation);

    
    
    SearchRequest searchRequest = new SearchRequest("user");
    searchRequest.types("type");
    searchRequest.source(sourceBuilder);
    
    
    try {
		SearchResponse searchResponse = client.search(searchRequest);

        System.out.println(sourceBuilder.toString());
		System.out.println("==========================================================");
		
		System.out.println(searchResponse);
		
		System.out.println("==========================================================");

		client.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void aggsearch() {
	// TODO Auto-generated method stub
    SearchRequest searchRequest = new SearchRequest("user");
    searchRequest.types("type");

    
	RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	
    //TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("dAge", "6");
    TermsAggregationBuilder termAgg = AggregationBuilders.terms("dAge").field("dAge");
    
    //sourceBuilder.query(termQueryBuilder);
    sourceBuilder.aggregation(termAgg);
    sourceBuilder.from(0);
    sourceBuilder.size(10);
    
    searchRequest.source(sourceBuilder);
    try {
    	System.out.println("====================================================================");
    	System.out.println(searchRequest.source().toString());
		SearchResponse searchResponse = client.search(searchRequest);
		
		Aggregations aggregations = searchResponse.getAggregations();
		ParsedLongTerms aggregation = aggregations.get("dAge");
		List<? extends Bucket> buckets = aggregation.getBuckets();
		for (Bucket bucket : buckets) {
			System.out.print("年龄:"+bucket.getKey()+";人数:");
			System.out.println(bucket.getDocCount());
		}

		System.out.println(searchResponse);
		client.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
}

//年龄为1，社会关系年龄为10
public static void t110() {
	// TODO Auto-generated method stub
	RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.from(0);
    sourceBuilder.size(10);
    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("dAge", "1");
    TermQueryBuilder termQueryBuilder2 = QueryBuilders.termQuery("shgx.dAge", "1");

    BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();
    
    boolBuilder.must(termQueryBuilder).must(termQueryBuilder2);
    
    
    sourceBuilder.query(boolBuilder);
    SearchRequest searchRequest = new SearchRequest("user");
    searchRequest.types("type");
    searchRequest.source(sourceBuilder);
    try {
    	System.out.println("====================================================================");
    	System.out.println(searchRequest.source().toString());
		SearchResponse searchResponse = client.search(searchRequest);
		System.out.println(searchResponse);
		client.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
}


public static void aggsearch2() {
	// TODO Auto-generated method stub
    SearchRequest searchRequest = new SearchRequest("user");
    searchRequest.types("type");

    
	RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("dAge");
	rangeQuery.from(1);
	rangeQuery.to(1);
	
    TermsAggregationBuilder termAgg = AggregationBuilders.terms("dAge").field("dAge");
    TermsAggregationBuilder shgxAgg = AggregationBuilders.terms("shgx.dAge").field("shgx.dAge");

    //sourceBuilder.query(rangeQuery);
    termAgg.subAggregation(shgxAgg);
    sourceBuilder.aggregation(termAgg);
    
    sourceBuilder.from(0);
    sourceBuilder.size(10);
    
    searchRequest.source(sourceBuilder);
    try {
    	System.out.println("====================================================================");
    	System.out.println(searchRequest.source().toString());
		SearchResponse searchResponse = client.search(searchRequest);
			
		Aggregations aggregations = searchResponse.getAggregations();
		ParsedLongTerms aggregation = aggregations.get("shgx.dAge");
		List<? extends Bucket> buckets = aggregation.getBuckets();
		for (Bucket bucket : buckets) {
			System.out.print("年龄:"+bucket.getKey()+";人数:");
			System.out.println(bucket.getDocCount());
		}

		System.out.println(searchResponse);
		client.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
}

public static void subAggSearch() {
	// TODO Auto-generated method stub
    SearchRequest searchRequest = new SearchRequest("user");
    searchRequest.types("type");

    
	RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	//RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("dAge");
	//rangeQuery.from(1);
	//rangeQuery.to(2);
	
    TermsAggregationBuilder termAgg = AggregationBuilders.terms("dAge").field("dAge");
    TermsAggregationBuilder shgxAgg = AggregationBuilders.terms("shgx.dAge").field("shgx.dAge");

    //sourceBuilder.query(rangeQuery);
    termAgg.subAggregation(shgxAgg);
    sourceBuilder.aggregation(termAgg);
    
    sourceBuilder.from(0);
    sourceBuilder.size(10);
    
    searchRequest.source(sourceBuilder);
    try {
    	System.out.println("====================================================================");
    	System.out.println(searchRequest.source().toString());
		SearchResponse searchResponse = client.search(searchRequest);
			
		Aggregations aggregations = searchResponse.getAggregations();
		ParsedLongTerms aggregation = aggregations.get("dAge");
		System.out.println("============================父聚合开始===============================");
		List<? extends Bucket> buckets = aggregation.getBuckets();
		for (Bucket bucket : buckets) {
			System.out.print("年龄:"+bucket.getKey()+";人数:");
			System.out.println(bucket.getDocCount());
			Aggregations subAggregations = bucket.getAggregations();
			ParsedLongTerms subaggregation = subAggregations.get("shgx.dAge");
			List<? extends Bucket> subBuckets = subaggregation.getBuckets();
			System.out.println("============================子聚合开始===============================");
			for (Bucket subBucket : subBuckets) {
				System.out.print("年龄:"+subBucket.getKey()+";人数:");
				System.out.println(subBucket.getDocCount());
			}
			System.out.println("============================子聚合结束===============================");

		}
		
		System.out.println("============================父聚合结束===============================");

		System.out.println(searchResponse);
		client.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
}

public static void searchSrjlAggByStat() {
	// TODO Auto-generated method stub
    SearchRequest searchRequest = new SearchRequest("user");
    searchRequest.types("type");

    
	RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	sourceBuilder.from(0);
	sourceBuilder.size(10);
	
    TermsAggregationBuilder aggregation = AggregationBuilders.terms("userName").field("userName.keyword");
    aggregation.subAggregation(AggregationBuilders.stats("stat_dSrje")
            .field("srjl.dSrje"));
    
    
    sourceBuilder.aggregation(aggregation);
    
    searchRequest.source(sourceBuilder);
    try {
    	System.out.println(searchRequest.source().toString());
		SearchResponse searchResponse = client.search(searchRequest);
			
		Aggregations aggregations = searchResponse.getAggregations();
		Terms byCompanyAggregation = aggregations.get("userName");
	    List<? extends Terms.Bucket> buckets = byCompanyAggregation.getBuckets();
	    for (Terms.Bucket bucket : buckets) {
	       Stats stat = bucket.getAggregations().get("stat_dSrje");
	       System.out.println("用户名："+bucket.getKey()); //获取分组名称
	       System.out.println("平均值："+stat.getAvg());
	       System.out.println("总数："+stat.getSum());
	       System.out.println("最大值："+stat.getMaxAsString());
	       System.out.println("最小值："+stat.getMin());
	    }
	    System.out.println(searchResponse);
		client.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
}

public static void searchSrjlAgg() {
	// TODO Auto-generated method stub
    SearchRequest searchRequest = new SearchRequest("user");
    searchRequest.types("type");

    
	RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	sourceBuilder.from(0);
	sourceBuilder.size(10);
	
    TermsAggregationBuilder aggregation = AggregationBuilders.terms("userName").field("userName.keyword");
    aggregation.subAggregation(AggregationBuilders.avg("avg_dSrje").field("srjl.dSrje"));
    aggregation.subAggregation(AggregationBuilders.sum("sum_dSrje").field("srjl.dSrje"));

    
    sourceBuilder.aggregation(aggregation);
    
    searchRequest.source(sourceBuilder);
    try {
    	System.out.println(searchRequest.source().toString());
		SearchResponse searchResponse = client.search(searchRequest);
			
		Aggregations aggregations = searchResponse.getAggregations();
		Terms byCompanyAggregation = aggregations.get("userName");
		
	    List<? extends Terms.Bucket> buckets = byCompanyAggregation.getBuckets();
	    
	    for (Terms.Bucket bucket : buckets) {
	       ParsedAvg avg = bucket.getAggregations().get("avg_dSrje");
	       ParsedSum sum = bucket.getAggregations().get("sum_dSrje");

	       System.out.println("用户名："+avg.getName()); //获取分组名称
	       System.out.println("平均值："+avg.getValue()); //获取平均值
	       System.out.println("总和："+sum.getValue()); //获取总和

	    }
	    System.out.println(searchResponse);
		client.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    
}
/**
 * 家庭住址聚合
 * 统计每个省，每个省下每个市的用户数
 */
public static void searchjtzz() {
	// TODO Auto-generated method stub
	SearchRequest searchRequest = new SearchRequest("user");
    searchRequest.types("type");

    
	RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	
    TermsAggregationBuilder province = AggregationBuilders.terms("province").field("cProvince.keyword");
    TermsAggregationBuilder city = AggregationBuilders.terms("city").field("cCity.keyword");
    TermsAggregationBuilder region = AggregationBuilders.terms("region").field("cRegion.keyword");
    city.subAggregation(region);
    province.subAggregation(city);
    sourceBuilder.aggregation(province);
    
    sourceBuilder.from(0);
    sourceBuilder.size(10);
    
    searchRequest.source(sourceBuilder);
    try {
    	System.out.println("====================================================================");
    	System.out.println(searchRequest.source().toString());
		SearchResponse searchResponse = client.search(searchRequest);
		
		SearchHits hits = searchResponse.getHits();
		long totalHits = hits.getTotalHits();
		System.out.println("=========查询结果总数:"+totalHits+"=============");
		
		ParsedStringTerms provinceAgg = searchResponse.getAggregations().get("province");
		List<? extends Bucket> provinceBukets = provinceAgg.getBuckets();
		
		for (Bucket provinceBuket : provinceBukets) {

			System.out.print("===省:"+provinceBuket.getKey()+";个数:");
			System.out.println(provinceBuket.getDocCount());
			
			ParsedStringTerms cityAgg = provinceBuket.getAggregations().get("city");
			List<? extends Bucket> cityBukets = cityAgg.getBuckets();
			
			for (Bucket cityBuket : cityBukets) {

				System.out.print("======市:"+cityBuket.getKey()+";个数:");
				System.out.println(cityBuket.getDocCount());
				
				ParsedStringTerms regionAgg = cityBuket.getAggregations().get("region");
				List<? extends Bucket> regionBukets = regionAgg.getBuckets();
				
				for (Bucket regionBuket : regionBukets) {
					System.out.print("=========县:"+regionBuket.getKey()+";个数:");
					System.out.println(regionBuket.getDocCount());

				}

			}
			System.out.println();
			System.out.println();

		}
		

		System.out.println(searchResponse);
		client.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
}
/**
 * 统计单个省或者单个市下的用户数
 */
public static void searchjtzz2() {
	// TODO Auto-generated method stub
	SearchRequest searchRequest = new SearchRequest("user");
    searchRequest.types("type");

    
	RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	
    TermQueryBuilder term1 = QueryBuilders.termQuery("cCity", "菏泽市");
    TermQueryBuilder term2 = QueryBuilders.termQuery("cCity", "济南市");

    
    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
    
    boolQuery.should(term1).should(term2);
    
    //TermsAggregationBuilder province = AggregationBuilders.terms("province").field("cProvince.keyword");
    TermsAggregationBuilder city = AggregationBuilders.terms("city").field("cCity.keyword");
    TermsAggregationBuilder region = AggregationBuilders.terms("region").field("cRegion.keyword");
    city.subAggregation(region);
    //province.subAggregation(city);
    
    sourceBuilder.aggregation(city);
    sourceBuilder.query(boolQuery);
    sourceBuilder.from(0);
    sourceBuilder.size(10);
    
    searchRequest.source(sourceBuilder);
    try {
    	System.out.println("====================================================================");
    	System.out.println(searchRequest.source().toString());
		SearchResponse searchResponse = client.search(searchRequest);
		
		SearchHits hits = searchResponse.getHits();
		long totalHits = hits.getTotalHits();
		System.out.println("=========查询结果总数:"+totalHits+"=============");
		//ParsedStringTerms provinceAgg = searchResponse.getAggregations().get("province");
		//List<? extends Bucket> provinceBukets = provinceAgg.getBuckets();
		
		//for (Bucket provinceBuket : provinceBukets) {

			//System.out.print("===省:"+provinceBuket.getKey()+";个数:");
			//System.out.println(provinceBuket.getDocCount());
		
			ParsedStringTerms cityAgg = searchResponse.getAggregations().get("city");
			List<? extends Bucket> cityBukets = cityAgg.getBuckets();
			
			for (Bucket cityBuket : cityBukets) {
			    System.out.print("======市:"+cityBuket.getKey()+";个数:");
				System.out.println(cityBuket.getDocCount());
				
				ParsedStringTerms regionAgg = cityBuket.getAggregations().get("region");
				List<? extends Bucket> regionBukets = regionAgg.getBuckets();
				
				for (Bucket regionBuket : regionBukets) {
					System.out.print("=========县:"+regionBuket.getKey()+";个数:");
					System.out.println(regionBuket.getDocCount());

				}

			}
			System.out.println();
			System.out.println();

		//}
		

		System.out.println(searchResponse);
		client.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
}

public static void searchShgx() {
	// TODO Auto-generated method stub
	SearchRequest searchRequest = new SearchRequest("user");
    searchRequest.types("type");

    
	RestHighLevelClient client = ElasticSearchUtil.getRestHighLevelClient("127.0.0.1", 9200);
	SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
	
    TermQueryBuilder term1 = QueryBuilders.termQuery("cId", "00d8a56518724086808aade2929218d0");
    TermQueryBuilder term2 = QueryBuilders.termQuery("cId", "0124521d8ebd406bac7ebedc53676c0f");

    BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
    boolQuery.should(term1).should(term2);
    
    //TermsAggregationBuilder province = AggregationBuilders.terms("province").field("cProvince.keyword");
    TermsAggregationBuilder city = AggregationBuilders.terms("city").field("cCity.keyword");
    TermsAggregationBuilder region = AggregationBuilders.terms("region").field("cRegion.keyword");
    city.subAggregation(region);
    //province.subAggregation(city);
    
    sourceBuilder.aggregation(city);
    sourceBuilder.query(boolQuery);
    sourceBuilder.from(0);
    sourceBuilder.size(10);
    
    searchRequest.source(sourceBuilder);
    try {
    	System.out.println("====================================================================");
    	System.out.println(searchRequest.source().toString());
		SearchResponse searchResponse = client.search(searchRequest);
		
		SearchHits hits = searchResponse.getHits();
		for (SearchHit searchHit : hits) {
			Map<String, Object> result = searchHit.getSourceAsMap();
			
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> shgxs = (List<Map<String, Object>>) result.get("shgx");
			for (Map<String, Object> map : shgxs) {
				System.out.println();
				System.out.print("用户:"+map.get("cUserId")+";cId:");
				System.out.print(map.get("cId")+";年龄:");
				System.out.println(map.get("dAge"));

				
			}
//			for (Shgx shgx : shgxs) {
//				System.out.println(shgx.getCUserId());
//				System.out.println(shgx.getCId());
//				System.out.println(shgx.getCName());
//				System.out.println(shgx.getDAge());
//
//			}
			
			
			
			System.out.println();
			
		}
		long totalHits = hits.getTotalHits();
		System.out.println("=========查询结果总数:"+totalHits+"=============");
		
		//ParsedStringTerms provinceAgg = searchResponse.getAggregations().get("province");
		//List<? extends Bucket> provinceBukets = provinceAgg.getBuckets();
		
		//for (Bucket provinceBuket : provinceBukets) {

			//System.out.print("===省:"+provinceBuket.getKey()+";个数:");
			//System.out.println(provinceBuket.getDocCount());
		
			ParsedStringTerms cityAgg = searchResponse.getAggregations().get("city");
			List<? extends Bucket> cityBukets = cityAgg.getBuckets();
			
			for (Bucket cityBuket : cityBukets) {
			    System.out.print("======市:"+cityBuket.getKey()+";个数:");
				System.out.println(cityBuket.getDocCount());
				
				ParsedStringTerms regionAgg = cityBuket.getAggregations().get("region");
				List<? extends Bucket> regionBukets = regionAgg.getBuckets();
				
				for (Bucket regionBuket : regionBukets) {
					System.out.print("=========县:"+regionBuket.getKey()+";个数:");
					System.out.println(regionBuket.getDocCount());

				}

			}
			System.out.println();
			System.out.println();

		//}
		

		System.out.println(searchResponse);
		client.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
}
	public static void main(String[] args) {
		//termSearch();
		//realNameSearch();
		//duoTiaojianSearch();
		///juhe();
		
		//matchSearch();
		
		//fuzzySearch();
		
		//aggsearch();
		//aggsearch2();
		//t110();
		//subAggSearch();
		
		//searchSrjlAgg();
		//searchSrjlAggByStat();
		//searchjtzz();
		//searchjtzz2();
		searchShgx();
	}


	
}
