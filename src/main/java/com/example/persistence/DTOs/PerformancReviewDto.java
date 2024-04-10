package com.example.persistence.DTOs;


import com.example.persistence.enums.PerformanceReating;
import jakarta.ws.rs.core.Link;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
public class PerformancReviewDto  implements Serializable {

    private Integer reviewId;
    private String reviwerName;
    private Integer reviwerId;
    private Date reviewDate;
    private PerformanceReating performanceRating;

    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    private List<Link> links;




}
