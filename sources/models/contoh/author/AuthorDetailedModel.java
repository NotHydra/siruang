package models.contoh.author;


import models.contoh.book.BookModel;

public class AuthorDetailedModel extends AuthorModel {
	private final BookModel book;

	public AuthorDetailedModel(int id, String name, int idBook, BookModel book) {
		super(id, name, idBook);

		this.book = book;
	}

	public BookModel getBook() {
		return this.book;
	}

	@Override
	public String toString() {
		return "AuthorDetailedModel("
				+ "id=" + this.getId() + ", "
				+ "name=" + this.getName() + ", "
				+ "idBook=" + this.getIdBook() + ", "
				+ "book=" + this.book
				+ ")";
	}
}
