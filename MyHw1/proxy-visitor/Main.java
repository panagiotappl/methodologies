import java.util.List;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class Conference implements ConferenceElement {
    private final String title;
    private final List<ConferenceElement> elements;

    public Conference(String title, List<ConferenceElement> elements) {
        this.title = title;
        this.elements = elements;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public void accept(ConferenceElementVisitor visitor) {
        visitor.visit(this);
        for (ConferenceElement element : elements) {
            element.accept(visitor);
        }
    }
}

class Participant implements ConferenceElement {
    private final String name;

    public Participant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(ConferenceElementVisitor visitor) {
        visitor.visit(this);
    }

}

class Contribution implements ConferenceElement {
    private final String title;
    private final List<ConferenceElement> materials;

    public Contribution(String title, List<ConferenceElement> materials) {
        this.title = title;
        this.materials = materials;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void accept(ConferenceElementVisitor visitor) {
        visitor.visit(this);
        for (ConferenceElement material : materials) {
            material.accept(visitor);
        }
    }

}

class Paper implements ConferenceElement {
    private final String title;

    public Paper(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void accept(ConferenceElementVisitor visitor) {
        visitor.visit(this);
    }

}

class Slide implements ConferenceElement {
    private final String title;

    public Slide(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public void accept(ConferenceElementVisitor visitor) {
        visitor.visit(this);
    }
}

// Visitor Interface
interface ConferenceElementVisitor {
    void visit(Conference conference);

    void visit(Participant participant);

    void visit(Contribution contribution);

    void visit(Paper paper);

    void visit(Slide slide);
}

// Conference Element Interface
interface ConferenceElement {
    void accept(ConferenceElementVisitor visitor);
}

class ConferenceHandler implements InvocationHandler {
    private Conference c;

    public ConferenceHandler(Conference c) {
        this.c = c;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (args.length == 1) {
            Object obj = args[0];
            Class cls = args[0].getClass();
            String className = cls.getName();
            if (className.equals("Conference")) {
                Conference c = (Conference) obj;
                System.out.print("*Conference: ");
                System.out.println(c.getTitle());
                System.out.println("|__");
            } else if (className.equals("Contribution")) {
                Contribution contrib = (Contribution) obj;
                System.out.print("  *Contribution: ");
                System.out.println(contrib.getTitle());
                System.out.println("  |__");
            } else if (className.equals("Paper")) {
                Paper p = (Paper) obj;
                System.out.print("    *Paper: ");
                System.out.println(p.getTitle());
                System.out.println("    |");
            } else if (className.equals("Slide")) {
                Slide s = (Slide) obj;
                System.out.print("    *Slide: ");
                System.out.println(s.getTitle());
                System.out.println("    |");
            } else if (className.equals("Participant")) {
                Participant part = (Participant) obj;
                System.out.print("  *Participant: ");
                System.out.println(part.getName());
                System.out.println("  |");
            }
        }
        return null;

    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Conference conference = new Conference("Bridges", List.of(
                new Contribution("The DOOMs syndrome", List.of(new Slide("dooms_slides.ppt"))),
                new Contribution("The Chiral Network",
                        List.of(new Slide("chiral_slides.ppt"), new Paper("chiral_network.pdf"))),
                new Contribution("Avoiding MULE camps", List.of(new Slide("mules.ppts"), new Paper("mule_camps.pdf"))),
                new Contribution("Leanirng the importance of Bridge Babies", List.of(new Paper("BBs.pdf"))),
                new Participant("Sam Porter"), new Participant("Amelie Strand")));
        Class[] interfaces = new Class[1];
        interfaces[0] = ConferenceElementVisitor.class;
        ConferenceElementVisitor proxy = (ConferenceElementVisitor) Proxy.newProxyInstance(Main.class.getClassLoader(),
                interfaces, new ConferenceHandler(conference));
        conference.accept(proxy);
    }
}