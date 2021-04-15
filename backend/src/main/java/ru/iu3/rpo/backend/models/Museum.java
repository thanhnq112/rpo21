package ru.iu3.rpo.backend.models;

import javax.persistence.*;

@Entity
@Table(name = "museum")
@Access(AccessType.FIELD)
public class Museum {

    public Museum() {}
    public Museum(Long id) { this.id = id; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    public long id;

    @Column(name = "name")
    public String name;

    @Column(name = "location")
    public String location;

//    @JsonIgnore
//    @ManyToMany
//    @JoinTable(name = "usermuseums",
//            joinColumns = @JoinColumn(name = "museumid"),
//            inverseJoinColumns = @JoinColumn (name = "userid"))
//    public Set<User> users = new HashSet<>();
}
