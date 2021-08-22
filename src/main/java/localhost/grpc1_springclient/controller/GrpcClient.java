package localhost.grpc1_springclient.controller;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import proto.GrpcRequest;
import proto.GrpcResponse;
import proto.ServiceGrpc;

@RestController
public class GrpcClient {

    @GetMapping("/")
    public String dd() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9000)
                .usePlaintext()
                .build();

        ServiceGrpc.ServiceBlockingStub stub =
                ServiceGrpc.newBlockingStub(channel);

        GrpcResponse helloResponse = stub.grpc(GrpcRequest.newBuilder()
                .setFirstName("Baeldung")
                .setLastName("gRPC")
                .build());

        channel.shutdown();

        return helloResponse.toString();
    }
}
