package com.example.moviebuff;

import java.util.List;

public class ResultsItem{
	String overview;
	String original_language;
	private String originalTitle;
	private boolean video;
	String title;
	private List<Integer> genreIds;
	String poster_path;
	private String backdropPath;
	String release_date;
	private double popularity;
	double vote_average;
	private int id;
	boolean adult;
	int vote_count;

	public void setOverview(String overview){
		this.overview = overview;
	}

	public String getOverview(){
		return overview;
	}

	public void setOriginalLanguage(String originalLanguage){
		this.original_language = originalLanguage;
	}

	public String getOriginalLanguage(){
		return original_language;
	}

	public void setOriginalTitle(String originalTitle){
		this.originalTitle = originalTitle;
	}

	public String getOriginalTitle(){
		return originalTitle;
	}

	public void setVideo(boolean video){
		this.video = video;
	}

	public boolean isVideo(){
		return video;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setGenreIds(List<Integer> genreIds){
		this.genreIds = genreIds;
	}

	public List<Integer> getGenreIds(){
		return genreIds;
	}

	public void setPosterPath(String posterPath){
		this.poster_path = posterPath;
	}

	public String getPosterPath(){
		return poster_path;
	}

	public void setBackdropPath(String backdropPath){
		this.backdropPath = backdropPath;
	}

	public String getBackdropPath(){
		return backdropPath;
	}

	public void setReleaseDate(String releaseDate){
		this.release_date = releaseDate;
	}

	public String getReleaseDate(){
		return release_date;
	}

	public void setPopularity(double popularity){
		this.popularity = popularity;
	}

	public double getPopularity(){
		return popularity;
	}

	public void setVoteAverage(double voteAverage){
		this.vote_average = voteAverage;
	}

	public double getVoteAverage(){
		return vote_average;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAdult(boolean adult){
		this.adult = adult;
	}

	public boolean isAdult(){
		return adult;
	}

	public void setVoteCount(int voteCount){
		this.vote_count = voteCount;
	}

	public int getVoteCount(){
		return vote_count;
	}
}