//package com.elena.fitnessserver.models;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Objects;
//
//@Entity
//@Table(name = "lessons")
//@IdClass(Lesson.LessonPK.class)
//public class Lesson {
//
//    public static class LessonPK implements Serializable {
//        protected long programId;
//        protected long id;
//
//        public LessonPK() {
//        }
//
//        public LessonPK(long programId, long id) {
//            this.programId = programId;
//            this.id = id;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            LessonPK lessonPK = (LessonPK) o;
//            return programId == lessonPK.programId &&
//                    id == lessonPK.id;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(programId, id);
//        }
//
//        @Override
//        public String toString() {
//            return "LessonPK{" +
//                    "programId=" + programId +
//                    ", id=" + id +
//                    '}';
//        }
//    }
//
//    @Id
//    private long id;
//
//    @Column(name = "date", nullable = false)
//    private String date;
//
//    @Column(name = "notes")
//    private String notes;
//
//    @ManyToOne
//    @JoinColumn(name = "instructors_id", nullable = false)
//    private Instructor instructor;
//
//    @Id
//    @Column(name="program_id", updatable = false, insertable = false)
//    private long programId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "program_id")
//    Program program;
//
//}
