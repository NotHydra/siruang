package models.contoh.book;


import models.contoh.genre.GenreModel;

public class BookDetailedModel extends BookModel {
	private final GenreModel[] genres;

	public BookDetailedModel(int id, String title, String description, GenreModel[] genres) {
		super(id, title, description);

		this.genres = genres;
	}

	public GenreModel[] getGenres() {
		return this.genres;
	}

	@Override
	public String toString() {
		return "BookDetailedModel("
				+ "id=" + this.getId() + ", "
				+ "title=" + this.getTitle() + ", "
				+ "description=" + this.getDescription() + ", "
				+ "genres=" + this.genres
				+ ")";
	}
}
