package com.example.member.member.service;

import com.example.grpc.MemberRequest;
import com.example.grpc.MemberResponse;
import com.example.grpc.MemberServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class MemberServiceImpl extends MemberServiceGrpc.MemberServiceImplBase {
    @Override
    public void getMember(MemberRequest request, StreamObserver<MemberResponse> responseObserver) {
        String name = request.getName();

        MemberResponse response = MemberResponse.newBuilder().setName(name).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
