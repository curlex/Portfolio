require_relative 'question.rb'
require_relative 'advanced_question.rb'
require_relative 'basic_question.rb'
require_relative 'intermediate_question.rb'
require_relative 'master_question.rb'

module Calculator
  def answer
    case self.last.operation
      when '+'
        result = self.last.x + self.last.y
      when '-'
        result= self.last.x - self.last.y
      when '*'
        result = self.last.x * self.last.y
      when '/'
      result = self.last.x / self.last.y
  end
    result
  end
end

class QuestionList
  include Calculator
  attr_reader :questions
  def initialize
    @questions = []
  end
  def generate_question (level)
    case level
      when 1
        @questions << BasicQuestion.new
      when 2
        @questions << IntermediateQuestion.new
      when 3
        @questions << AdvancedQuestion.new
      when 4
        @questions << MasterQuestion.new
    end

  end
  def each
    0.upto @questions.size-1 do |i|
       yield @questions[i]
    end
  end
  def last
    @questions.last
  end
  def to_s
    self.each do |question|
      question.to_s
    end
  end
end