package com.adonis.pulsedesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommentService {
    @Autowired
    private HuggingFaceService huggingFaceService;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private HuggingFaceService aiService;

    public Comment submitComment(Comment comment) {
        Comment savedComment = commentRepository.save(comment);

        Map<String, String> aiResult = aiService.analyzeComment(comment.getText());

        boolean isTicket = Boolean.parseBoolean(aiResult.get("isTicket"));

        if (isTicket) {
            Ticket ticket = new Ticket();

            ticket.setTitle(aiResult.get("summary"));
            ticket.setDescription(comment.getText());
            ticket.setCategory(aiResult.get("category"));
            ticket.setPriority(aiResult.get("priority"));

            ticketRepository.save(ticket);
        }

        return savedComment;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
    public Map<String, String> processComment(Comment comment) {

        Map<String, String> aiResult = huggingFaceService.analyzeComment(comment.getText());
        if (Boolean.parseBoolean(aiResult.get("isTicket"))) {
            Ticket ticket = new Ticket();
            ticket.setTitle(aiResult.get("summary"));
            ticket.setDescription(comment.getText());
            ticket.setCategory(aiResult.get("category"));
            ticket.setPriority(aiResult.get("priority"));

            ticketRepository.save(ticket);
        }

        return aiResult; // 🔥 IMPORTANT
    }
}