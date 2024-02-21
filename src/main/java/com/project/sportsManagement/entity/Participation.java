package com.project.sportsManagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "participation")
public class Participation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "participation_id")
    private int participationId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id",referencedColumnName = "student_id")
    private Student studentId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "game_code",referencedColumnName = "game_code")
    private EventGame gameCode;

    public Participation() {
    }

    public Participation(Student studentId, EventGame gameCode) {
        this.studentId = studentId;
        this.gameCode = gameCode;
    }

    public int getParticipationId() {
        return participationId;
    }

    public void setParticipationId(int participationId) {
        this.participationId = participationId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public EventGame getGameCode() {
        return gameCode;
    }

    public void setGameCode(EventGame gameCode) {
        this.gameCode = gameCode;
    }
}