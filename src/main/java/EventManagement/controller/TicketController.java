package EventManagement.controller;

import EventManagement.model.GeneralEvent;
import EventManagement.model.SpecialEvent;
import EventManagement.model.Ticket;
import EventManagement.model.User;
import EventManagement.repository.GeneralEventRepository;
import EventManagement.repository.SpecialEventRepository;
import EventManagement.repository.TicketRepository;
import EventManagement.repository.UserRepository;

import EventManagement.security.services.UserDetailsServiceImpl;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    UserDetailsServiceImpl service;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    SpecialEventRepository specialEventRepository;
    @Autowired
    GeneralEventRepository generalEventRepository;

   /* @GetMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<Ticket> getAllTicket() {
        return ticketRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    public Ticket saveTicket(@RequestBody Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Optional<Ticket> getTicket(@PathVariable("id") String id) {
        return ticketRepository.findById(id);
    }*/

    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    @GetMapping("/send-mail/{username}/{id}")
    public String sendMail(@PathVariable("username") String username, @PathVariable("id") String id) throws MessagingException, FileNotFoundException {
        User user = userRepository.findUserByUsername(username);
        SpecialEvent specialEvent = specialEventRepository.findSpecialEventById(id);

        String dest = "E:\\ticket.pdf";
        PdfWriter pdfWriter = new PdfWriter(dest);

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.addNewPage();

        String heading = specialEvent.getEventName();
        String description = specialEvent.getEventDescription();

        Paragraph paragraph1 = new Paragraph(heading);
        Paragraph paragraph2 = new Paragraph(description);

        Document document = new Document(pdfDocument);

        document.add(paragraph1);
        document.add(paragraph2);

        document.close();

        try {
            service.sendMail(user);
        } catch (MailException e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
        System.out.println("I was called");
        return "Message Sent";
    }

}
