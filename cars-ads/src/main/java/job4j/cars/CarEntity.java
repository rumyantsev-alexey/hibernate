package job4j.cars;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "car")
public class CarEntity implements ProjectCars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "note")
    private String note;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "users_id")
    private UsersEntity user;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "mark_id")
    private MarkEntity mark;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "model_id")
    private ModelEntity model;

    @Column(name = "price")
    private Integer price;

    @Column(name = "issue")
    private Integer issue;

    @Column(name = "dist")
    private Integer dist;

    @Column(name = "enginecapacity")
    private Integer enginecapacity;

    @Column(name = "power")
    private Integer power;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "transmission_id")
    private TransmissionEntity trans;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "bodytype_id")
    private BodytypeEntity btype;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "enginestype_id")
    private EnginestypeEntity etype;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "driveunit_id")
    private DriveunitEntity dunit;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "wheel_id")
    private WheelEntity wheel;

    @Column(name = "created")
    private Timestamp created;

    @Column(name = "old")
    private boolean old;

    @OneToMany(mappedBy = "car", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<FotoEntity> fotos;

    public CarEntity() {

    }

    public CarEntity(final String note) {
        this.note = note;
        this.old = false;
        this.created = new Timestamp(System.currentTimeMillis());
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isOld() {
        return old;
    }

    public void setOld(boolean old) {
        this.old = old;
    }

    public Integer getDist() {
        return dist;
    }

    public void setDist(Integer dist) {
        this.dist = dist;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Set<FotoEntity> getFotos() {
        return fotos;
    }

    public void setFotos(Set<FotoEntity> fotos) {
        this.fotos = fotos;
    }

    public MarkEntity getMark() {
        return mark;
    }

    public void setMark(MarkEntity mark) {
        this.mark = mark;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public ModelEntity getModel() {
        return model;
    }

    public void setModel(ModelEntity model) {
        this.model = model;
    }

    public TransmissionEntity getTrans() {
        return trans;
    }

    public void setTrans(TransmissionEntity trans) {
        this.trans = trans;
    }

    public BodytypeEntity getBtype() {
        return btype;
    }

    public void setBtype(BodytypeEntity btype) {
        this.btype = btype;
    }

    public EnginestypeEntity getEtype() {
        return etype;
    }

    public void setEtype(EnginestypeEntity etype) {
        this.etype = etype;
    }

    public DriveunitEntity getDunit() {
        return dunit;
    }

    public void setDunit(DriveunitEntity dunit) {
        this.dunit = dunit;
    }

    public WheelEntity getWheel() {
        return wheel;
    }

    public void setWheel(WheelEntity wheel) {
        this.wheel = wheel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getIssue() {
        return issue;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }

    public Integer getEnginecapacity() {
        return enginecapacity;
    }

    public void setEnginecapacity(Integer enginecapacity) {
        this.enginecapacity = enginecapacity;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CarEntity carEntity = (CarEntity) o;

        if (note != null ? !note.equals(carEntity.note) : carEntity.note != null) {
            return false;
        }
        if (user != null ? !user.equals(carEntity.user) : carEntity.user != null) {
            return false;
        }
        if (city != null ? !city.equals(carEntity.city) : carEntity.city != null) {
            return false;
        }
        return model != null ? model.equals(carEntity.model) : carEntity.model == null;
    }

    @Override
    public int hashCode() {
        int result = note != null ? note.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }
}
