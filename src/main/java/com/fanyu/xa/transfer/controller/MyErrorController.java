package com.fanyu.xa.transfer.controller;

import com.fanyu.xa.transfer.entity.AsynResponse;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequestMapping({"${server.error.path:${error.path:/error}}"})
public class MyErrorController extends AbstractErrorController {
    private final ErrorProperties errorProperties;

    public MyErrorController(ErrorAttributes errorAttributes, ErrorProperties errorProperties,
            List<ErrorViewResolver> errorViewResolvers) {
        super(errorAttributes, errorViewResolvers);
        Assert.notNull(errorProperties, "ErrorProperties must not be null");
        this.errorProperties = errorProperties;
    }

    @RequestMapping(
            produces = {"text/html"}
    )
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = this.getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(
                this.getErrorAttributes(request, this.getErrorAttributeOptions(request, MediaType.TEXT_HTML)));
        response.setStatus(status.value());
        ModelAndView modelAndView = this.resolveErrorView(request, response, status, model);
        return modelAndView != null ? modelAndView : new ModelAndView("error", model);
    }

    @RequestMapping
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return new ResponseEntity(status);
        } else {
//            AsynResponse asynResponse = AsynResponse.builder().build();
//            StringBuilder stringBuilder = new StringBuilder();
//            try {
//                String line;
//                BufferedReader reader = request.getReader();
//                while ((line = reader.readLine()) != null) {
//                    stringBuilder.append(line).append('\n');
//                }
//                ObjectMapper objectMapper = new ObjectMapper();
//                JsonNode jsonNode = objectMapper.readTree(stringBuilder.toString());
//                if (jsonNode.has("reqId")) {
//                    String reqId = jsonNode.get("reqId").asText();
//                    asynResponse.setReqId(reqId);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            Map<String, Object> body = this.getErrorAttributes(request,
//                    this.getErrorAttributeOptions(request, MediaType.ALL));
//            asynResponse.setErrorCode(body.get("status").toString());
//            asynResponse.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            return new ResponseEntity(AsynResponse.notFound(), status);
        }
    }

    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    public ResponseEntity<String> mediaTypeNotAcceptable(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        return ResponseEntity.status(status).build();
    }

    protected ErrorAttributeOptions getErrorAttributeOptions(HttpServletRequest request, MediaType mediaType) {
        ErrorAttributeOptions options = ErrorAttributeOptions.defaults();
        if (this.errorProperties.isIncludeException()) {
            options = options.including(new ErrorAttributeOptions.Include[]{ErrorAttributeOptions.Include.EXCEPTION});
        }

        if (this.isIncludeStackTrace(request, mediaType)) {
            options = options.including(new ErrorAttributeOptions.Include[]{ErrorAttributeOptions.Include.STACK_TRACE});
        }

        if (this.isIncludeMessage(request, mediaType)) {
            options = options.including(new ErrorAttributeOptions.Include[]{ErrorAttributeOptions.Include.MESSAGE});
        }

        if (this.isIncludeBindingErrors(request, mediaType)) {
            options = options.including(
                    new ErrorAttributeOptions.Include[]{ErrorAttributeOptions.Include.BINDING_ERRORS});
        }

        return options;
    }

    protected boolean isIncludeStackTrace(HttpServletRequest request, MediaType produces) {
        switch (this.getErrorProperties().getIncludeStacktrace()) {
            case ALWAYS:
                return true;
            case ON_PARAM:
                return this.getTraceParameter(request);
            default:
                return false;
        }
    }

    protected boolean isIncludeMessage(HttpServletRequest request, MediaType produces) {
        switch (this.getErrorProperties().getIncludeMessage()) {
            case ALWAYS:
                return true;
            case ON_PARAM:
                return this.getMessageParameter(request);
            default:
                return false;
        }
    }

    protected boolean isIncludeBindingErrors(HttpServletRequest request, MediaType produces) {
        switch (this.getErrorProperties().getIncludeBindingErrors()) {
            case ALWAYS:
                return true;
            case ON_PARAM:
                return this.getErrorsParameter(request);
            default:
                return false;
        }
    }

    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }
}