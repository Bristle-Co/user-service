package com.bristle.userservice.controller;

import com.bristle.proto.common.ApiError;
import com.bristle.proto.common.ResponseContext;
import com.bristle.proto.user.GetUsersRequest;
import com.bristle.proto.user.GetUsersResponse;
import com.bristle.proto.user.User;
import com.bristle.proto.user.UserServiceGrpc;
import com.bristle.userservice.service.UserService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@GrpcService
public class UserServiceGrpcController extends UserServiceGrpc.UserServiceImplBase {


    public final UserService m_userService;

    Logger log = LoggerFactory.getLogger(UserServiceGrpcController.class);

    UserServiceGrpcController(UserService userService) {
        this.m_userService = userService;
    }

    @Override
    public void getUsers(GetUsersRequest request, StreamObserver<GetUsersResponse> responseObserver) {
        String requestId = request.getRequestContext().getRequestId();
        log.info("Request id: " + requestId + " ,getUsers grpc request received");
        ResponseContext.Builder responseContextBuilder = ResponseContext.newBuilder();
        responseContextBuilder.setRequestId(requestId);
        GetUsersResponse.Builder responseBuilder = GetUsersResponse.newBuilder();
        try {
            List<User> customerList = m_userService.getUsers();
            responseObserver.onNext(
                    responseBuilder
                            .addAllUser(customerList)
                            .setResponseContext(responseContextBuilder).build());

        } catch (Exception e) {
            log.error("Request id: " + requestId + " " + e.getMessage());
            responseContextBuilder.setError(ApiError.newBuilder()
                    .setErrorMessage(e.getMessage())
                    .setExceptionName(e.getClass().getName()));

            responseObserver.onNext(responseBuilder
                    .setResponseContext(responseContextBuilder.build()).build());
        }
        responseObserver.onCompleted();
    }
}
