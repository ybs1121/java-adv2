package webservice;

import io.member.Member;
import io.member.MemberRepository;
import was.httpserver.HttpRequest;
import was.httpserver.HttpResponse;
import was.httpserver.servlet.annotation.Mapping;

import java.util.List;

import static util.MyLogger.log;

public class MemberController {
    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Mapping("/")
    public void home(HttpResponse response) {
        String str = "<html><body>"
                + "<h1>Member Manager</h1>"
                + "<ul>"
                + "<li><a href=\"/members\">Member</a></li>"
                + "<li><a href=\"/add-member-form\">Add Member</a></li>"
                + "</ul>";
        response.writeBody(str);
    }

    @Mapping("/members")
    public void members(HttpResponse response) {
        List<Member> members = memberRepository.findAll();

        StringBuffer page = new StringBuffer();
        page.append("<html><body>");
        page.append("<h1>Members</h1>");
        page.append("<ul>");
        for (Member member : members) {

            page.append("<li>")
                    .append("ID : ").append(member.getId())
                    .append(" Name : ").append(member.getName())
                    .append("</li>");
        }

        page.append("</ul>");
        page.append("<a href=\"/\">back to home</a>");
        page.append("</body></html>");
    }

    @Mapping("/add-member-form")
    public void addMemberForm(HttpResponse response) {
        String body =
                "<meta charset=\"UTF-8\">" +
                        "<html><body>" +
                        "<h1> Add ne Member </h1>" +
                        "<form method='POST' action='/add-member' accept-charset='UTF-8'>" +
                        "ID : <input type= 'text' name='id' <br>"
                        + "Name : <input type= 'text' name='name' <br>"
                        + "Age : <input type= 'text' name='age' <br>"
                        + "<input type= 'submit' value='Add Member'>"
                        + " </form>"
                        + " <a href=\"/\">back to home</a>"
                        + "</body></html>";

        response.writeBody(body);
    }

    @Mapping("/add-member")
    public void addMember(HttpRequest request, HttpResponse response) {
        log("MemberController.addMember");
        log("request = " + request);

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(id, name, age);
        memberRepository.add(member);


        response.writeBody("<h1> save Ok </h1>");
        response.writeBody("<a href=\"/\">back to home</a>");
    }
}
