package br.com.erudio.data.DTO.v1;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@JsonPropertyOrder({"Id","author", "title", "price", "title"})
public class BooksDTO extends RepresentationModel<BooksDTO> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("Id")
	@Mapping("id")
	private long key;	
	private String author;	
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd 'T'HH:mm:ss.SSSXXX", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date launchDate;
	
	private float price;
	private String title;
	
	public BooksDTO() {
		
	}

	public long getKey() {
		return key;
	}

	public void setKey(long key) {
		this.key = key;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getlaunchDate() {
		return launchDate;
	}

	public void setlaunchDate(Date launchDate) {
		this.launchDate = launchDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(author, key, launchDate, price, title);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BooksDTO other = (BooksDTO) obj;
		return Objects.equals(author, other.author) && key == other.key
				&& Objects.equals(launchDate, other.launchDate)
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(title, other.title);
	}

	
	
	
	
	
	

}
