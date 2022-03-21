package jpa.practice2.api;

import jpa.practice2.domain.Address;
import jpa.practice2.domain.Member;
import jpa.practice2.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member) {
        Long id = memberService.join((member));
        return new CreateMemberResponse(id);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request) {

        Member member = new Member(request.getName(), null);

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);

    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Valid UpdateMemberRequest request) {
        memberService.update(id, request.getName());
        Member findMember = memberService.findMember(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Data
    static class UpdateMemberRequest {
        private String name;
    }

    @Data
    @AllArgsConstructor
    static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @GetMapping("/api/v3/members")
    public List<Member> membersV3() {
        return memberService.findMembers();
    }

    @GetMapping("/api/v4/members")
    public Result membersV4() {
        List<Member> findMembers = memberService.findMembers();
        //엔티티 -> DTO 변환
        List<MemberDto> collect = findMembers.stream()
                .map(m -> new MemberDto(m.getName()))
                .collect(Collectors.toList());
        return new Result(collect);
    }

    @Data
    static class CreateMemberResponse {
        private Long id;

        public CreateMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class CreateMemberRequest {
        private String name;
    }

    @PostConstruct
    public void init() {
        Member member1 = new Member("user1", new Address("서울", "노원", "111"));
        Member member2 = new Member("user2", new Address("서울", "강북", "222"));

        memberService.join(member1);
        memberService.join(member2);
    }

    @Data
    static class Result<T> {
        private T data;

        public Result(T data) {
            this.data = data;
        }
    }

    @Data
    static class MemberDto {
        private String name;

        public MemberDto(String name) {
            this.name = name;
        }
    }

}
