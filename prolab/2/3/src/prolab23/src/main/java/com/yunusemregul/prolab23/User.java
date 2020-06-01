package com.yunusemregul.prolab23;

/**
 * Giriş yapmış kullanıcıyı temsil eden sınıf.
 *
 * Sadece 1 tane giriş yapmış kullanıcı olabileceği için DataManager ile benzer
 * şekilde uygulama genelinde sadece 1 tane instancesi bulunması için Singleton
 * patternini kullandım.
 */
public class User
{
	private static User instance;

	public int id;
	public String name;
	private Movie movie;
	public int chapter = 1;
	public int rate = -1;
	public int watchTime = 0;

	public static User getInstance()
	{
		if (instance == null)
		{
			instance = new User();
		}

		return instance;
	}
	
	public void setMovie(Movie movie)
	{
		this.movie = movie;
		DataManager.getInstance().loadMovie(this);
	}
	
	public Movie getMovie()
	{
		return this.movie;
	}

	public void saveMovieData()
	{
		DataManager.getInstance().saveMovie(this);
	}
}
