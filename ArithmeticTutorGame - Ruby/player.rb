class Player
  def initialize name
    @name = name
  end
  def play
    welcome
    while !game_over?
      ask_question
      get_answer
      check_answer
      advance?

    end
    summary
  end
end