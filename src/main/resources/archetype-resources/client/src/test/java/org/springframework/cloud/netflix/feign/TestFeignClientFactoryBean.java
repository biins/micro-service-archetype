package org.springframework.cloud.netflix.feign;

import org.springframework.core.annotation.AnnotationUtils;

public class TestFeignClientFactoryBean<T> extends FeignClientFactoryBean {

    private final Class<T> cls;

    public TestFeignClientFactoryBean(Class<T> cls) {
        this.cls = cls;
        initialize();
    }

    private void initialize() {
        FeignClient feignClient = AnnotationUtils.findAnnotation(this.cls, FeignClient.class);

        this.setType(cls);
        this.setName(feignClient.name());
        this.setUrl(feignClient.url());
        this.setPath(feignClient.path());
        this.setDecode404(feignClient.decode404());
        this.setFallback(feignClient.fallback());
    }
}
