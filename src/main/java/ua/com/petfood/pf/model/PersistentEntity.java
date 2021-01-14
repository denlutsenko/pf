package ua.com.petfood.pf.model;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public class PersistentEntity<ID extends Serializable> implements Persistable<ID> {
  private static final long serialVersionUID = 0L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected ID id;

  @Override
  public ID getId() {
    return id;
  }

  public void setId(final ID id) {
    this.id = id;
  }

  @Override
  @Transient
  public boolean isNew() {
    return null == getId();
  }

  @Override
  public String toString() {
    return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersistentEntity<?> that = (PersistentEntity<?>) o;
    return id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
