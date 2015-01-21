require 'test/unit'
require_relative 'arithmetic_tutor'
class ArithmeticTutorTest<Test::Unit::TestCase
  def setup
    @arithmetic_tutor = ArithmeticTutor.new 'Asma',4,1234
  end
  def test_check_answer
    @arithmetic_tutor.ask_question
    @arithmetic_tutor.current_answer=-3
    @arithmetic_tutor.check_answer
    assert(@arithmetic_tutor.current_answer==:Correct,'Check answer method is not working')
  end
  def test_advance
    @arithmetic_tutor.current_answer = :Correct
    @arithmetic_tutor.previous_answer = :Correct
    @arithmetic_tutor.advance?
    assert(@arithmetic_tutor.level.level==2,'Advance is not leveling up')
    assert(@arithmetic_tutor.previous_answer==:Initial,'Previous answer is not being set to different symbol to start checking over')
    @arithmetic_tutor.current_answer = :Incorrect
    @arithmetic_tutor.previous_answer = :Incorrect
    @arithmetic_tutor.advance?
    assert(@arithmetic_tutor.level.level==1,'Advance is not leveling down')

    assert(@arithmetic_tutor.number_of_questions==2,'Advance is not decreasing the number of question')
    @arithmetic_tutor.current_answer = :Incorrect
    @arithmetic_tutor.previous_answer = :Correct
    @arithmetic_tutor.advance?
    assert(@arithmetic_tutor.previous_answer==:Incorrect,'Previous answer is not being changed')

  end
  def test_game_over
    4.times do
      @arithmetic_tutor.current_answer = :Correct
      @arithmetic_tutor.previous_answer = :Correct
      @arithmetic_tutor.advance?
    end
    assert(@arithmetic_tutor.game_over?,'Game over method not working')
  end
end