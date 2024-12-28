package io.member;

import io.member.impl.DataMemberRepository;
import io.member.impl.FileMemberRepository;
import io.member.impl.MemoryMemberRepository;
import io.member.impl.ObjectMemberRepository;

import java.util.List;
import java.util.Scanner;

public class MemberConsoleMain {

//    private static final MemberRepository repository = new MemoryMemberRepository();
//    private static final MemberRepository repository = new FileMemberRepository();
//    private static final MemberRepository repository = new DataMemberRepository();
    private static final MemberRepository repository = new ObjectMemberRepository();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. 회원 등록 | 2. 회원목록조회 | 3. 종료");
            System.out.print("선택 : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerMember(scanner);
                    break;
                case 2:
                    displayMembers();
                    break;
                case 3:
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("잘못된 선택 ");
            }
        }
    }


    private static void registerMember(Scanner scanner) {
        System.out.print("ID : ");
        String id = scanner.nextLine();
        System.out.print("Name : ");
        String name = scanner.nextLine();
        System.out.print("Age : ");
        Integer age = scanner.nextInt();
        scanner.nextLine();

        Member member = new Member(id, name, age);
        repository.add(member);
        System.out.println("회원등록 완료");
    }

    private static void displayMembers() {
        List<Member> members = repository.findAll();
        for (Member member : members) {
            System.out.println(member.toString());
        }
    }
}
