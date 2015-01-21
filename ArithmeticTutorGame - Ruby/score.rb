module Score
  attr_reader :score
  def initial_score
    @score = 0
  end
  def update_score by
    @score+=by
  end
end