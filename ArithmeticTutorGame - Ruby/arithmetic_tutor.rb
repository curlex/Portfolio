require_relative 'question_list.rb'
require_relative 'level.rb'
require_relative 'results.rb'
require_relative 'score.rb'
require_relative 'player.rb'

class ArithmeticTutor < Player
  include Score
  attr_accessor :current_answer,:previous_answer
  attr_reader :number_of_questions,:level

  def initialize (name,number_of_questions,seed)
    super name
    srand(seed)
    @number_of_questions = number_of_questions
    @level = Level.new 4,1
    @question_list = QuestionList.new
    @results = Results.new
    @previous_answer = :Initial
    initial_score
  end

  def welcome
    puts "Welcome to the Arithmetic Tutor,#{@name}\nThere are #{@number_of_questions} questions to do!"
  end

  def ask_question
    @question_list.generate_question @level.level
    puts @question_list.last.to_s
  end

  def get_answer
    @current_answer = gets.chomp.to_i
  end

  def check_answer
    @current_answer == @question_list.answer ? @current_answer = :Correct : @current_answer = :Incorrect
    add_result
    score
  end

  def advance?
    if @current_answer==@previous_answer
      @current_answer==:Correct ? @level.advance_a_level : @level.drop_a_level
      @previous_answer = :Initial
    else
      @previous_answer = @current_answer
    end
    @number_of_questions-=1
  end

  def score
    @current_answer == :Correct ? (update_score @level.level ): (update_score 0)
  end

  def add_result
    @results.add @question_list.last.to_s, @current_answer
  end

  def game_over?
     @number_of_questions<=0 ? true : false
  end

  def summary
    puts @results.to_s
    puts to_s
  end

  def to_s
    "Game Over!\nLevel: #{@question_list.last.name}\nScore: #{@score}"
  end

end
